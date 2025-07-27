package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.PasswordResetDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.user.service.UserService;
import com.kurung.common.security.service.SessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "사용자 관련 API")
@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;

    @GetMapping("/tokenuser")
    public ResponseEntity<UserDTO> getMyInfo() {
        UserDTO currentUser = sessionService.getUserFromToken();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

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

    // 비밀번호 재설정
    @Operation(summary = "비밀번호 재설정", description = "사용자의 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/reset-password")
    public ResponseEntity<PasswordResetDTO> resetPassword(@RequestBody PasswordResetDTO request) {
        UserDTO currentUser = sessionService.getUserFromToken();
        if (currentUser == null) {
            return new ResponseEntity<>(
                PasswordResetDTO.builder()
                    .message("로그인이 필요합니다.")
                    .success(false)
                    .build(),
                HttpStatus.UNAUTHORIZED
            );
        }
        
        PasswordResetDTO result = userService.resetPassword(currentUser.getUserUuid(), request);
        HttpStatus status = result.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(result, status);
    }

    // 인증번호 발송 (통합)
    @Operation(summary = "인증번호 발송", description = "회원가입 또는 비밀번호 재설정을 위한 인증번호를 발송합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "인증번호 발송 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/send-verification-code")
    public ResponseEntity<HttpStatus> sendVerificationCode(@RequestBody VerificationCodeDTO request) {
        userService.sendVerificationCode(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 인증번호 확인
    @Operation(summary = "인증번호 확인", description = "발송된 인증번호를 확인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "인증번호 확인 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/confirm-verification-code")
    public ResponseEntity<HttpStatus> confirmVerificationCode(@RequestBody VerificationCodeDTO request) {
        userService.confirmVerificationCode(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 비밀번호 재설정 (이메일 기반)
    @Operation(summary = "비밀번호 재설정", description = "이메일 인증 후 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @PostMapping("/reset-password-by-email")
    public ResponseEntity<HttpStatus> resetPasswordByEmail(@RequestBody VerificationCodeDTO request) {
        userService.resetPasswordByEmail(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 비밀번호 재설정 (로그인된 사용자)
    @Operation(summary = "비밀번호 재설정", description = "로그인된 사용자의 비밀번호를 재설정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "비밀번호 재설정 성공"),
        @ApiResponse(responseCode = "400", description = "잘못된 요청"),
        @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/reset-password-loggedin")
    public ResponseEntity<PasswordResetDTO> resetPasswordLoggedIn(@RequestBody VerificationCodeDTO request) {
        UserDTO currentUser = sessionService.getUserFromToken();
        if (currentUser == null) {
            return new ResponseEntity<>(
                PasswordResetDTO.builder()
                    .message("로그인이 필요합니다.")
                    .success(false)
                    .build(),
                HttpStatus.UNAUTHORIZED
            );
        }
        
        return new ResponseEntity<>(userService.resetPassword(currentUser.getUserUuid(), request), HttpStatus.OK);
    }
}
