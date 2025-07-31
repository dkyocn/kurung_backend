package com.kurung.common.security.handler;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

  private final JWTUtil jwtUtil;
  private final UserService userService;
  private final RestTemplate restTemplate;

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {

    String authorization = request.getHeader("Authorization");

    if (authorization != null && !authorization.isEmpty()) {
      // Bearer 접두사 제거
      String accessToken = authorization.replace("Bearer ", "").trim();

      try {
        String userUuid = jwtUtil.getUserUuidFromToken(accessToken); //토큰에서 Uuid꺼냄

        if (userUuid != null) {
          UserDTO userByUserUuid = userService.getUserByUuid(userUuid); //Uuid로 유저 DTO 조회
          
          // 소셜 로그인 사용자인지 확인
          if (userByUserUuid.getUserPath() == UserPath.KAKAO || 
              userByUserUuid.getUserPath() == UserPath.NAVER) {
            
            log.info("소셜 로그인 사용자 로그아웃 처리 - 사용자: {}, 경로: {}", 
                userUuid, userByUserUuid.getUserPath());
            
            // 소셜 플랫폼에서 토큰 해제
            revokeSocialToken(userByUserUuid);
          }
          
          // 백엔드에서 refresh token 삭제
          userService.updateRefresh(userByUserUuid, new String());

          // JSON 응답 형식으로 통일
          response.setStatus(HttpServletResponse.SC_OK);
          response.setContentType(MediaType.APPLICATION_JSON_VALUE);
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write("{\"message\":\"로그아웃 성공\",\"status\":\"success\"}");
        }
      } catch (Exception e) {
        log.error("로그아웃 처리 중 오류 발생", e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
          response.getWriter().write("{\"message\":\"로그아웃 처리 중 오류 발생\",\"status\":\"error\"}");
        } catch (Exception e1) {
          e1.printStackTrace();
        }
      }
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setCharacterEncoding("UTF-8");
      try {
        response.getWriter().write("{\"message\":\"유효하지 않은 요청\",\"status\":\"error\"}");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }
  }
  
  // 소셜 플랫폼에서 토큰 해제
  private void revokeSocialToken(UserDTO userDTO) {
    try {
      if (userDTO.getUserPath() == UserPath.KAKAO) {
        // 카카오 토큰 해제
        String kakaoRevokeUrl = "https://kapi.kakao.com/v1/user/logout";
        // TODO: 카카오 액세스 토큰으로 토큰 해제 API 호출
        // 현재는 소셜 액세스 토큰을 저장하지 않아서 구현 불가
        log.info("카카오 토큰 해제 요청 - 사용자: {}", userDTO.getUserUuid());
        
      } else if (userDTO.getUserPath() == UserPath.NAVER) {
        // 네이버 토큰 해제
        String naverRevokeUrl = "https://nid.naver.com/oauth2.0/token";
        // TODO: 네이버 액세스 토큰으로 토큰 해제 API 호출
        // 현재는 소셜 액세스 토큰을 저장하지 않아서 구현 불가
        log.info("네이버 토큰 해제 요청 - 사용자: {}", userDTO.getUserUuid());
      }
    } catch (Exception e) {
      log.error("소셜 토큰 해제 중 오류 발생", e);
      // 소셜 토큰 해제 실패해도 백엔드 로그아웃은 계속 진행
    }
  }
}

