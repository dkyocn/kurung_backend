package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 1. 사용자 조회 (기존 코드)
    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDTO> getUserByUuid(@PathVariable String userUuid) {
        return ResponseEntity.ok(userService.getUserByUuid(userUuid));
    }

    // 2. 사용자 로그인 (추가된 코드)
    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userEntity) {
        UserEntity result = userService.login(userEntity); //
        return ResponseEntity.ok(result);
    }
}
