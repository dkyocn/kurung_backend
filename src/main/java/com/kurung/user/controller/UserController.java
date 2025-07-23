package com.kurung.user.controller;

import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.dto.WithdrawalRequestDTO;
import com.kurung.user.dto.WithdrawalResponseDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    public final UserRepository userRepository;

    // SessionService 테스트
    @GetMapping("/tokenuser")
    public ResponseEntity<UserDTO> getMyInfo(HttpServletRequest request) {
        return new ResponseEntity<>(sessionService.getUserFromToken(request), HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Void> registerUser(@RequestBody UserEntity user) {
        boolean registerUserResult = userService.registerUser(user);
        log.info("회원 가입 요청 - 사용자 ID: {}", user.getUserId());
        log.info("회원 가입 성공 여부: {}", registerUserResult);
        return registerUserResult ?
            new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 아이디 중복 확인
    @Operation(summary = "아이디 중복 확인", description = "사용 가능한 아이디인지 확인합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "확인 성공")
    })
    @GetMapping("/check-userid")
    public ResponseEntity<Map<String, Object>> checkuserId(@RequestParam String userId) {

        boolean available = userService.checkUserIdAvailability(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("available", available);
        response.put("userid", userId);
        response.put("message", available ? "사용 가능한 아이디입니다ㅣ." : "이미 사용 중인 아이디입니다."); //서비스 로직 + true
        return new ResponseEntity<>(HttpStatus.OK); //response.ok 수정 필요
    }



}
