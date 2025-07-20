package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.kurung.common.security.service.SessionService;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SessionService sessionService;

    // 사용자 조회 (기존 기능)
    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDTO> getUserByUuid(@PathVariable String userUuid) {
        return ResponseEntity.ok(userService.getUserByUuid(userUuid));
    }

    //SessionService 테스트
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyInfo(HttpServletRequest request){
        return ResponseEntity.ok(sessionService.getUserFromToken(request));

    }


// 아이디 중복 체크
// 회원 가입
// 비밀번호 암호화
// 회원 탈퇴
// 회원 정보 수정 (비밀번호 변경 포함)

}
