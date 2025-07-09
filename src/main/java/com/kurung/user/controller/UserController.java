package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 조회
    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDTO> getUserByUuid(@PathVariable String userUuid) {
        // 없는 사용자일 경우 예외 없이 바로 반환 (무조건 있다고 가정)
        return ResponseEntity.ok(userService.getUserByUuid(userUuid));
    }
}
