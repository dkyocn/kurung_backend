package com.kurung.common.handler;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.service.RefreshService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final JWTUtil jwtUtil;
    private final RefreshService refreshService;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // "Bearer " 제거
            try {
                String userId = jwtUtil.getUserIdFromToken(token);
                refreshService.deleteRefreshToken(userId); // ✅ 추가된 부분
                log.info("[로그아웃 성공] 사용자 ID: {} - RefreshToken 삭제 완료", userId);
            } catch (Exception e) {
                log.error("[로그아웃 실패] 토큰 파싱 오류: {}", e.getMessage());
            }
        } else {
            log.warn("[로그아웃 요청] Authorization 헤더가 없거나 Bearer 형식이 아님");
        }
    }
}
