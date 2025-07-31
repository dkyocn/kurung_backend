package com.kurung.common.security.filter;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserService userService; // 추가

    public JWTFilter(JWTUtil jwtUtil, UserService userService) { // 생성자 수정
        this.jwtUtil = jwtUtil;
        this.userService = userService; // 추가
    }

    // 토큰 검사를 생략할 URL 목록 정의
    private boolean isExcludedUrl(String url) {
        return url.equals("/") ||
            url.equals("/favicon.ico") ||
            url.equals("/api/v1/kurung/user/login") ||
            url.equals("/api/v1/kurung/user/signup") ||
            url.equals("/api/v1/kurung/user/check-userid") ||
            url.equals("/api/v1/kurung/user/check-email-duplicate") ||
            url.equals("/api/v1/kurung/user/send-verification-code") ||
            url.equals("/api/v1/kurung/user/confirm-verification-code") ||
            url.equals("/api/v1/kurung/user/reset-password-by-email") ||
            url.startsWith("/api/v1/kurung/user/kakao/") ||  // 카카오 소셜 로그인 경로
            url.startsWith("/api/v1/kurung/user/naver/") ||  // 네이버 소셜 로그인 경로
            url.startsWith("/api/v1/kurung/test/social/") ||  // 소셜 로그인 테스트 경로
            url.startsWith("/api-test") ||
            url.startsWith("/swagger-ui/") ||
            url.startsWith("/v3/api-docs") ||
            url.equals("/reissue") ||
            url.endsWith(".png") ||
            url.startsWith("/js/") ||
            url.startsWith("/css/") ||
            url.startsWith("/api/personality-test/") ||
            url.equals("/user/login") ||
            url.startsWith("/api/psychological-test/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 디버그용 로그 추가
        log.info("=== JWT 필터 진입 ===");
        log.info("요청 URL: {}", requestURI);
        log.info("제외 목록 체크 결과: {}", isExcludedUrl(requestURI));

        // 로그인, 회원가입 등은 필터 검사 없이 바로 통과
        if (isExcludedUrl(requestURI)) {
            log.info("토큰 검사 없이 통과됨: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        log.info("제외 목록에 없음 - 토큰 검증 시작");

        // 제외 목록이 아닌 경우에만 토큰 추출 및 검증
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader("RefreshToken");

        if (accessToken == null || accessToken.isEmpty()) {
            log.warn("Authorization 헤더가 비어있음");
        }
        if (refreshToken == null || refreshToken.isEmpty()) {
            log.warn("RefreshToken 헤더가 비어있음");
        }

        try {
            // 토큰이 모두 있을 때만 검증
            if (accessToken != null && refreshToken != null) {

                boolean isAccessExpired = jwtUtil.isTokenExpired(accessToken);
                boolean isRefreshExpired = jwtUtil.isTokenExpired(refreshToken);

                // 토큰 시간 만료시 세션 아웃
                if (!isAccessExpired && isRefreshExpired) {
                    log.warn("AccessToken 유효, RefreshToken 만료 - 강제 로그아웃 처리");
                    
                    // 데이터베이스에서 refresh token 삭제하여 강제 로그아웃
                    try {
                        String userUuid = jwtUtil.getUserUuidFromToken(accessToken);
                        UserDTO userDTO = userService.getUserByUuid(userUuid);
                        userService.updateRefresh(userDTO, ""); // 빈 문자열로 설정하여 로그아웃
                        log.info("Refresh Token 만료로 인한 강제 로그아웃 처리 완료 - userId: {}", userUuid);
                    } catch (Exception e) {
                        log.error("강제 로그아웃 처리 중 오류 발생", e);
                    }
                    
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setHeader("token-expired", "RefreshToken");
                    response.getWriter().write("{\"error\":\"RefreshToken expired\"}");
                    return;
                }

                if (isAccessExpired && !isRefreshExpired) {
                    log.warn("AccessToken 만료, RefreshToken 유효 → ReIssueController에 전달");
                    filterChain.doFilter(request, response);
                    return;
                }

                if (isAccessExpired && isRefreshExpired) {
                    log.warn("AccessToken & RefreshToken 모두 만료");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setHeader("token-expired", "Both");
                    response.getWriter().write("{\"error\":\"Both tokens expired\"}");
                    return;
                }

                // 둘 다 유효 → 다음 필터로 넘김
                filterChain.doFilter(request, response);
                return;
            }

            // 헤더가 없거나 하나라도 누락되면
            log.warn("AccessToken과 RefreshToken 모두 누락됨");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"현재 토큰이 없는 상태입니다.\"}");
        } catch (Exception e) {
            log.error("JWTFilter 처리 중 에러 발생", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"internal server error\"}");
        }
    }
}