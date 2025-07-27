package com.kurung.user.controller;

import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.UserDTO;

import com.kurung.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.PasswordChangeRequestDTO;
import com.kurung.user.dto.PasswordChangeResponseDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.common.dto.ApiResponseDTO;

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
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "회원가입 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 아이디(이메일) 중복 확인
    @Operation(summary = "아이디 중복 확인", description = "사용 가능한 아이디인지 확인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "확인 성공")
    })
    @GetMapping("/check-userid")
    public ResponseEntity<Boolean> checkuserId(@RequestParam String userId) {
        boolean available = userService.checkUserIdAvailability(userId);
        return new ResponseEntity<>(available, HttpStatus.OK); // 메시지 없이 true/false만 응답
    }

    // 비밀번호 변경
    @Operation(summary = "비밀번호 변경", description = "사용자의 비밀번호를 변경합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/change-password")
    public ResponseEntity<PasswordChangeResponseDTO> changePassword(@RequestBody PasswordChangeRequestDTO request) {
        UserDTO currentUser = sessionService.getUserFromToken();
        if (currentUser == null) {
            return new ResponseEntity<>(
                PasswordChangeResponseDTO.builder()
                    .message("로그인이 필요합니다.")
                    .success(false)
                    .build(),
                HttpStatus.UNAUTHORIZED
            );
        }
        
        PasswordChangeResponseDTO result = userService.changePassword(currentUser.getUserUuid(), request);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }

    // 인증번호 발송
    @Operation(summary = "인증번호 발송", description = "비밀번호 재설정을 위한 인증번호를 발송합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "인증번호 발송 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/send-verification-code")
    public ResponseEntity<ApiResponseDTO<String>> sendVerificationCode(@RequestBody VerificationCodeDTO request) {
        return new ResponseEntity<>(userService.sendVerificationCode(request), HttpStatus.OK);
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인", description = "발송된 인증번호를 확인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "인증번호 확인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/confirm-verification-code")
    public ResponseEntity<ApiResponseDTO<String>> confirmVerificationCode(@RequestBody VerificationCodeDTO request) {
        return new ResponseEntity<>(userService.confirmVerificationCode(request), HttpStatus.OK);
    }

    // 비밀번호 재설정 (이메일 기반)
    @Operation(summary = "비밀번호 재설정", description = "이메일 인증 후 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/reset-password-by-email")
    public ResponseEntity<ApiResponseDTO<String>> resetPasswordByEmail(@RequestBody VerificationCodeDTO request) {
        return new ResponseEntity<>(userService.resetPasswordByEmail(request), HttpStatus.OK);
    }

    // 비밀번호 재설정 (로그인된 사용자)
    @Operation(summary = "비밀번호 재설정", description = "로그인된 사용자의 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<PasswordChangeResponseDTO> resetPassword(@RequestBody VerificationCodeDTO request) {
        UserDTO currentUser = sessionService.getUserFromToken();
        if (currentUser == null) {
            return new ResponseEntity<>(
                PasswordChangeResponseDTO.builder()
                    .message("로그인이 필요합니다.")
                    .success(false)
                    .build(),
                HttpStatus.UNAUTHORIZED
            );
        }
        
        return new ResponseEntity<>(userService.resetPassword(currentUser.getUserUuid(), request), HttpStatus.OK);
    }
}
