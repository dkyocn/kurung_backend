package com.kurung.common.filter;

import com.kurung.common.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Authorization 헤더에서 토큰 꺼내기
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 제거

            try {
                // 2. 토큰 검증
                String userId = jwtUtil.getUserIdFromToken(token);
                boolean isExpired = jwtUtil.isTokenExpired(token);

                if (!isExpired) {
                    // TODO: 유저 정보 설정 (SecurityContext 등)
                    // 이 부분은 CustomUserDetailsService랑 연동해도 되고, 간단히 넘어가도 돼
                }
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"잘못된 토큰입니다.\"}");
                return;
            }
        }

        // 3. 다음 필터로 이동
        filterChain.doFilter(request, response);
    }
}
