package com.kurung.common.security.controller;

import com.kurung.common.util.JWTUtil;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReIssueController {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/api/v1/kurung/reissue")
    public ResponseEntity<?> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        log.info("ReissueController 실행");

        String accessTokenHeader = request.getHeader("Authorization");
        String refreshTokenHeader = request.getHeader("RefreshToken");
        String extendLogin = request.getHeader("ExtendLogin");

        log.info("accessTokenHeader: {}", accessTokenHeader);
        log.info("refreshTokenHeader: {}", refreshTokenHeader);
        log.info("extendLogin: {}", extendLogin);

        try {
            String accessToken = (accessTokenHeader != null && accessTokenHeader.startsWith("Bearer "))
                    ? accessTokenHeader.substring("Bearer ".length()).trim()
                    : null;

            String refreshToken = (refreshTokenHeader != null && refreshTokenHeader.startsWith("Bearer "))
                    ? refreshTokenHeader.substring("Bearer ".length()).trim()
                    : null;

            if (accessToken == null || refreshToken == null) {
                log.warn("RefreshToken 또는 AccessToken이 제공되지 않았습니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid tokens");
            }

            boolean isAccessTokenExpired = jwtUtil.isTokenExpired(accessToken);
            boolean isRefreshTokenExpired = jwtUtil.isTokenExpired(refreshToken);
            String userIdFromToken = jwtUtil.getUseridFromToken(accessToken); // 추가된 변수 선언

            log.info("토큰 만료 상태 - userId: {}, accessToken 만료: {}, refreshToken 만료: {}",
                    userIdFromToken, isAccessTokenExpired, isRefreshTokenExpired);

            // access 유효 + refresh 만료 + 로그인 연장 요청
            if (!isAccessTokenExpired && isRefreshTokenExpired) {
                if ("true".equalsIgnoreCase(extendLogin)) {
                    UserDTO userDTO = userService.getUserByUuid(jwtUtil.getUserUuidFromToken(accessToken));

                    String newRefreshToken = jwtUtil.generateToken(userDTO, "refresh");
                    userService.updateRefresh(userDTO, newRefreshToken);

                    log.info("RefreshToken 재발급 완료 - userId: {}, 시간: {}", userIdFromToken, LocalDateTime.now());

                    response.setHeader("Refresh-Token", "Bearer " + newRefreshToken);
                    response.setHeader("Access-Control-Expose-Headers", "Refresh-Token");
                    return ResponseEntity.ok("RefreshToken Reissued");
                }
            }

            // access + refresh 둘 다 만료
            if (isAccessTokenExpired && isRefreshTokenExpired) {
                UserDTO userDTO = userService.getUserByUuid(userIdFromToken);
                userService.updateRefresh(userDTO, new String());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login session expired");
            }

            // access 만료 + refresh 유효
            if (isAccessTokenExpired && !isRefreshTokenExpired) {
                String userIdFromRefresh = jwtUtil.getUseridFromToken(refreshToken);
                UserDTO userDTO = userService.getUserByUuid(userIdFromRefresh);
                UserEntity userEntity = UserEntity.createUserBuilder()
                        .userDTO(userDTO)
                        .build();

                String newAccessToken = jwtUtil.generateToken(userDTO, "access");

                log.info("AccessToken 재발급 완료 - userId: {}, 시간: {}", userIdFromRefresh, LocalDateTime.now());

                response.setHeader("Authorization", "Bearer " + newAccessToken);
                response.setHeader("Access-Control-Expose-Headers", "Authorization");
                return ResponseEntity.ok("Access Token Reissued");
            }

        } catch (Exception e) {
            log.error("/reissue 요청 처리 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Reissue Token");
        }

        return ResponseEntity.ok("Reissue Token");
    }
}
