package com.kurung.user.controller;

import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.service.UserService;
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
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.registerUserWithResponse(userDTO), HttpStatus.OK);
    }
    @GetMapping("/check-userid")
    public ResponseEntity<Boolean> checkuserId(@RequestParam String userId) {
        return new ResponseEntity<>(userService.checkUserIdAvailability(userId), HttpStatus.OK);
    }
    @PostMapping("/kakao/login")
    public ResponseEntity<UserDTO> kakaoLogin(@RequestBody UserDTO request) {
        return new ResponseEntity<>(userService.socialLoginWithResponse(request.getSocialToken(), UserPath.KAKAO), HttpStatus.OK);
    }
    @PostMapping("/naver/login")
    public ResponseEntity<UserDTO> naverLogin(@RequestBody UserDTO request) {
        return new ResponseEntity<>(userService.socialLoginWithResponse(request.getSocialToken(), UserPath.NAVER), HttpStatus.OK);
    }
    @PostMapping("/send-verification-code")
    public ResponseEntity<String> sendVerificationCode(@RequestBody UserDTO request) {
        userService.sendVerificationCode(request);
        return new ResponseEntity<>("인증번호가 발송되었습니다.", HttpStatus.OK);
    }
    @PostMapping("/confirm-verification-code")
    public ResponseEntity<String> confirmVerificationCode(@RequestBody UserDTO request) {
        boolean isValid = userService.confirmVerificationCode(request);
        return new ResponseEntity<>(isValid ? "인증번호가 확인되었습니다." : "인증번호가 일치하지 않습니다.",
            isValid ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }
    @PostMapping("/reset-password-by-email")
    public ResponseEntity<UserDTO> resetPasswordByEmail(@RequestBody UserDTO request) {
        return new ResponseEntity<>(userService.resetPasswordByEmail(request), HttpStatus.OK);
    }
}
