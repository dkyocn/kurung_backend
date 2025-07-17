package com.kurung.user.controller;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    // 사용자 조회 (기존 기능)
    @GetMapping("/{userUuid}")
    public ResponseEntity<UserDTO> getUserByUuid(@PathVariable String userUuid) {
        return ResponseEntity.ok(userService.getUserByUuid(userUuid));
    }
//
//    // 아이디 중복 체크
//    @PostMapping("/idchk")
//    public ResponseEntity<?> checkUserIdDuplicate(@RequestParam("userId") String userId) {
//        boolean exists = userService.existsByUserId(userId);
//        return ResponseEntity.ok(Map.of("result", exists ? "duplicated" : "ok"));
//    }
//
//    // 회원 가입
//    @PostMapping("/signup")
//    public ResponseEntity<?> signupUser(@RequestBody UserDTO userDTO) {
//        log.info("회원가입 요청: {}", userDTO.getUserId());
//
//        // 비밀번호 암호화
//        String encodedPwd = passwordEncoder.encode(userDTO.getUserPwd());
//        userDTO.setUserPwd(encodedPwd);
//
//        try {
//            userService.createUser(userDTO);
//            return ResponseEntity.ok(Map.of("message", "회원가입 성공"));
//        } catch (Exception e) {
//            log.error("회원가입 실패", e);
//            return ResponseEntity.internalServerError().body("회원가입 실패");
//        }
//    }
//
//    // 회원 탈퇴
//    @DeleteMapping("/delete/{userUuid}")
//    public ResponseEntity<?> deleteUser(@PathVariable String userUuid) {
//        try {
//            userService.deleteUser(userUuid);
//            return ResponseEntity.ok(Map.of("message", "회원 삭제 완료"));
//        } catch (Exception e) {
//            log.error("회원 삭제 실패", e);
//            return ResponseEntity.internalServerError().body("회원 삭제 실패");
//        }
//    }
//
//    // 회원 정보 수정 (비밀번호 변경 포함)
//    @PutMapping("/update/{userUuid}")
//    public ResponseEntity<?> updateUser(@PathVariable String userUuid,
//                                        @RequestBody UserDTO userDTO) {
//        try {
//            if (userDTO.getUserPwd() != null && !userDTO.getUserPwd().isBlank()) {
//                userDTO.setUserPwd(passwordEncoder.encode(userDTO.getUserPwd()));
//            }
//            userService.updateUser(userUuid, userDTO);
//            return ResponseEntity.ok(Map.of("message", "회원 수정 완료"));
//        } catch (Exception e) {
//            log.error("회원 수정 실패", e);
//            return ResponseEntity.internalServerError().body("회원 수정 실패");
//        }
//    }
}
