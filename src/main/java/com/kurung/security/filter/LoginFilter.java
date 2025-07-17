package com.kurung.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kurung.security.jwt.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();  // ✅ 재사용 가능

    public LoginFilter(AuthenticationManager authenticationManager,
                       JWTUtil jwtUtil,
                       UserService userService) {
        this.setAuthenticationManager(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userService = userService;

        // 로그인 요청 경로 설정
        this.setFilterProcessesUrl("/api/v1/kurung/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String userId = null;
        String userPwd = null;

        try {
            Map<String, Object> requestBody = objectMapper.readValue(request.getInputStream(), Map.class);
            log.info("requestBody = {}", requestBody);
            userId = (String) requestBody.get("userId");
            userPwd = (String) requestBody.get("userPwd");
        } catch (IOException e) {
            throw new RuntimeException("요청 데이터를 읽을 수 없습니다.", e);
        }

        if (userId == null || userPwd == null) {
            throw new RuntimeException("아이디 또는 비밀번호가 전달되지 않았습니다.");
        }

        log.info("로그인 시도 - userId: {}, userPwd 길이: {}", userId, userPwd.length());

        return this.getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(userId, userPwd));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication)
            throws IOException, ServletException {
        log.info("로그인 성공 로직 실행");

        String userId = authentication.getName();
        UserDTO user = userService.getUserByUserId(userId);

        if (user == null) {
            throw new RuntimeException("로그인 사용자를 찾을 수 없습니다. userId=" + userId);
        }

        if (!user.isActive()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"로그인 정지 회원입니다. 관리자에게 문의하세요\"}");
            return;
        }

        String accessToken = jwtUtil.generateToken(user, "access");
        String refreshToken = jwtUtil.generateToken(user, "refresh");

        log.info("토큰 발급 완료 - accessToken 만료: {}, refreshToken 만료: {}",
                new Date(System.currentTimeMillis() + jwtUtil.getAccessExpiration()),
                new Date(System.currentTimeMillis() + jwtUtil.getRefreshExpiration()));
//        userService.saveRefresh(new RefreshToken(UUID.randomUUID().toString(), userId, refreshToken));
        userService.updateRefresh(user, refreshToken);

        Map<String, Object> responseBody = Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken,
                "userId", userId,
                "userName", user.getUserNick(),
                "role", user.isAdminYN() ? "ADMIN" : "USER"
        );

        response.setContentType("application/json; charset=utf-8");
        objectMapper.writeValue(response.getWriter(), responseBody);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json; charset=utf-8");

        log.error("로그인 실패 - 예외 타입: {}", failed.getClass().getSimpleName());
        log.error("로그인 실패 - 메시지: {}", failed.getMessage());

        String errorMessage;
        if (failed.getMessage().contains("Bad credentials")) {
            errorMessage = "아이디와 비밀번호를 다시 확인해 주세요.";
        } else if (failed.getMessage().contains("사용자를 찾을 수 없습니다")) {
            errorMessage = "존재하지 않는 사용자입니다.";
        } else {
            errorMessage = "로그인 실패: 서버 내부 오류.";
        }

        objectMapper.writeValue(response.getWriter(), Map.of("error", errorMessage));
    }
}
