package com.kurung.user.controller;

import com.kurung.common.security.service.SessionService;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    public final UserRepository userRepository;
  
    @GetMapping("/tokenuser")
    public ResponseEntity<UserDTO> getMyInfo() {
        return new ResponseEntity<>(sessionService.getUserFromToken(), HttpStatus.OK);
    }
  
    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserDTO userDTO) {
        Map<String, String> response = new HashMap<>();

        try {
            // 이메일 형식 검증
            if (userDTO.getUserId() == null || !userDTO.getUserId().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // UserDTO를 UserEntity로 변환
            UserEntity user = UserEntity.builder()
                .userId(userDTO.getUserId())
                .userPwd(userDTO.getUserPwd())
                .userNick(userDTO.getUserNick())
                .userGender(userDTO.getUserGender() != null ? userDTO.getUserGender() : Gender.MALE) // 기본값 MALE
                .userPath(userDTO.getUserPath() != null ? userDTO.getUserPath() : UserPath.NORMAL)
                .isActive(true) // 기본값 1(활성)
                .adminYN(false) // 기본값 0(일반사용자)
                .userFaceLoginYN(false) // 기본값 0(FALSE)
                .build();

            boolean registerUserResult = userService.registerUser(user);
            log.info("회원 가입 요청 - 사용자 ID: {}", userDTO.getUserId());
            log.info("회원 가입 성공 여부: {}", registerUserResult);

            if (registerUserResult) {
                response.put("message", "회원가입이 성공적으로 완료되었습니다.");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("error", "이미 존재하는 이메일입니다.");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생", e);
            response.put("error", "회원가입 처리 중 오류가 발생했습니다: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
}
