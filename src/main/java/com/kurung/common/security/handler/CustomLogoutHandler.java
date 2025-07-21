package com.kurung.common.security.handler;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
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
  private final UserService userService;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    String authorization = request.getHeader("Authorization");

    if (authorization != null && !authorization.isEmpty()) {
      String accessToken = authorization.trim();

      try {
        String userUuid = jwtUtil.getUserUuidFromToken(accessToken); //토큰에서 Uuid꺼냄

        if (userUuid != null) {
          UserDTO userByUserUuid = userService.getUserByUuid(userUuid); //Uuid로 유저 DTO 조회
          userService.updateRefresh(userByUserUuid, new String());

          response.setStatus(HttpServletResponse.SC_OK);
          response.getWriter().write("로그아웃 성공");
        }
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        try {
          response.getWriter().write("로그아웃 처리 중 오류 발생");
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      try {
        response.getWriter().write("유효하지 않은 요청");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }
}

