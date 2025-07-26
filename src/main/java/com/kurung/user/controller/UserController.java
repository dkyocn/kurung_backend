package com.kurung.user.controller;

import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.UserSignupResponseDTO;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.service.UserService;
import com.kurung.user.social.dto.SocialLoginRequest;
import com.kurung.user.social.dto.SocialLoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    @GetMapping("/tokenuser")
    public ResponseEntity<UserDTO> getMyInfo() {
        return new ResponseEntity<>(sessionService.getUserFromToken(), HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserSignupResponseDTO result = userService.registerUserWithResponse(userDTO);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }

    // 아이디(이메일) 중복 확인
    @Operation(summary = "아이디 중복 확인", description = "사용 가능한 아이디인지 확인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "확인 성공")
    })
    @GetMapping("/check-userid")
    public ResponseEntity<Boolean> checkuserId(@RequestParam String userId) {
        boolean available = userService.checkUserIdAvailability(userId);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }
    
    // 카카오 로그인 (실제 소셜 API 연동용)
    @Operation(summary = "카카오 로그인", description = "카카오 액세스 토큰으로 로그인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/kakao/login")
    public ResponseEntity<SocialLoginResponseDTO> kakaoLogin(@RequestBody SocialLoginRequest request) {
        SocialLoginResponseDTO result = userService.socialLoginWithResponse(request.getSocialToken(), UserPath.KAKAO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 네이버 로그인 (실제 소셜 API 연동용)
    @Operation(summary = "네이버 로그인", description = "네이버 액세스 토큰으로 로그인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/naver/login")
    public ResponseEntity<SocialLoginResponseDTO> naverLogin(@RequestBody SocialLoginRequest request) {
        SocialLoginResponseDTO result = userService.socialLoginWithResponse(request.getSocialToken(), UserPath.NAVER);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
