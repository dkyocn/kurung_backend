package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.service.UserService;
import com.kurung.user.social.dto.KakaoUserInfo;
import com.kurung.user.social.dto.NaverUserInfo;
import com.kurung.user.social.dto.SocialLoginRequest;
import com.kurung.user.social.dto.SocialUserInfo;
import com.kurung.user.social.dto.SocialLoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/test/social")
@RequiredArgsConstructor
public class SocialLoginTestController {

    private final UserService userService;

    // 카카오 로그인 테스트
    @Operation(summary = "카카오 로그인 테스트", description = "카카오 로그인을 테스트합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/kakao/login")
    public ResponseEntity<SocialLoginResponseDTO> kakaoLoginTest(@RequestBody SocialLoginRequest request) {
        SocialLoginResponseDTO result = userService.testSocialLogin(UserPath.KAKAO, request.getSocialUserId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 네이버 로그인 테스트
    @Operation(summary = "네이버 로그인 테스트", description = "네이버 로그인을 테스트합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/naver/login")
    public ResponseEntity<SocialLoginResponseDTO> naverLoginTest(@RequestBody SocialLoginRequest request) {
        SocialLoginResponseDTO result = userService.testSocialLogin(UserPath.NAVER, request.getSocialUserId());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 소셜 회원 조회 테스트
    @Operation(summary = "소셜 회원 조회 테스트", description = "소셜 회원 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "회원 없음")
    })
    @GetMapping("/user/{userPath}/{socialUserId}")
    public ResponseEntity<Map<String, Object>> getSocialUserTest(
            @PathVariable UserPath userPath,
            @PathVariable String socialUserId) {
        Map<String, Object> response = new HashMap<>();

        try {
            UserDTO user = userService.getUserBySocialInfo(userPath, socialUserId);
            
            if (user != null) {
                response.put("message", "소셜 회원 조회 성공");
                response.put("userInfo", user);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message", "해당 소셜 회원을 찾을 수 없습니다.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.error("소셜 회원 조회 테스트 중 오류 발생", e);
            response.put("error", "소셜 회원 조회 중 오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
} 