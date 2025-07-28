package com.kurung.common.security.filter;

import com.kurung.common.util.JWTUtil;
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

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // 토큰 검사를 생략할 URL 목록 정의
    private boolean isExcludedUrl(String url) {
        return url.equals("/") ||
            url.equals("/favicon.ico") ||
            url.equals("/api/v1/kurung/user/login") ||
            url.equals("/api/v1/kurung/user/signup") ||
            url.equals("/api/v1/kurung/user/check-userid") ||
            url.equals("/api/v1/kurung/user/send-verification-email") ||
            url.equals("/api/v1/kurung/user/verify-code") ||
            url.equals("/api/v1/kurung/user/send-verification-code") ||
            url.equals("/api/v1/kurung/user/confirm-verification-code") ||
            url.equals("/api/v1/kurung/user/reset-password-by-email") ||
            url.startsWith("/api-test") ||
            url.startsWith("/swagger-ui/") ||
            url.startsWith("/v3/api-docs") ||
            url.equals("/reissue") ||
            url.endsWith(".png") ||
            url.startsWith("/js/") ||
            url.startsWith("/css/") ||
            url.startsWith("/api/personality-test/") ||
            url.startsWith("/api/psychological-test/");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 로그인, 회원가입 등은 필터 검사 없이 바로 통과
        if (isExcludedUrl(requestURI)) {
            log.info("토큰 검사 없이 통과됨: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 추출
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

                // 경우별 판단
                if (!isAccessExpired && isRefreshExpired) {
                    log.warn("AccessToken 유효, RefreshToken 만료");
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
