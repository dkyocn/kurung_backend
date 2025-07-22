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
        log.info("회원 가입 사용자 이름 : " + user.getUserId() + ", 비밀번호 : " + user.getUserPwd());
        log.info("회원 가입 성공 여부 : " + registerUserResult);
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

        return ResponseEntity.ok(response); //response.ok 수정 필요
    }







//    // 회원 탈퇴
//    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 처리합니다. 30일 이내 철회 가능합니다.")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "탈퇴 성공",
//            content = @Content(schema = @Schema(implementation = WithdrawalResponseDTO.class))),
//        @ApiResponse(responseCode = "400", description = "잘못된 요청 - 비밀번호 불일치 또는 동의 미확인"),
//        @ApiResponse(responseCode = "401", description = "인증 실패")
//    })
//    @PostMapping("/withdrawal")
//    public ResponseEntity<WithdrawalResponseDTO> withdrawUser(
//        @Valid @RequestBody WithdrawalRequestDTO request, HttpServletRequest httpRequest) {
//
//        UserDTO currentUser = sessionService.getUserFromToken();
//        if (currentUser == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // 서비스
//        }
//      return null;
//    }








//        // ===== 내정보 조회 =====
//
//        @Operation(summary = "내 프로필 조회", description = "현재 로그인한 사용자의 프로필 정보를 조회합니다.")
//        @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "조회 성공",
//                content = @Content(schema = @Schema(implementation = UserProfileDTO.class))),
//            @ApiResponse(responseCode = "401", description = "인증 실패")
//        })
//
//        @GetMapping("/profile")
//        public ResponseEntity<UserProfileDTO> getMyProfile() {
//            UserDTO currentUser = sessionService.getUserFromToken();
//            if (currentUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//
//            UserProfileDTO profile = userService.getUserProfile(currentUser.getUserUuid());
//            return ResponseEntity.ok(profile);
//        }
//
//        @Operation(summary = "내 프로필 수정", description = "닉네임, 프로필 이미지 등을 수정합니다.")
//        @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "수정 성공",
//                content = @Content(schema = @Schema(implementation = UserProfileDTO.class))),
//            @ApiResponse(responseCode = "400", description = "잘못된 요청 - 닉네임 중복 등"),
//            @ApiResponse(responseCode = "401", description = "인증 실패")
//        })
//
//        @PutMapping("/profile")
//        public ResponseEntity<UserProfileDTO> updateMyProfile(
//            @Valid @RequestBody UserProfileUpdateDTO updateDTO) {
//
//            UserDTO currentUser = sessionService.getUserFromToken();
//            if (currentUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//            log.info("프로필 수정 요청 - userUuid: {}", currentUser.getUserUuid());
//
//            UserProfileDTO updatedProfile = userService.updateUserProfile(currentUser.getUserUuid(), updateDTO);
//            return ResponseEntity.ok(updatedProfile);
//        }
//
//        @Operation(summary = "비밀번호 변경", description = "현재 비밀번호 확인 후 새 비밀번호로 변경합니다.")
//        @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "변경 성공"),
//            @ApiResponse(responseCode = "400", description = "잘못된 요청 - 현재 비밀번호 불일치 등"),
//            @ApiResponse(responseCode = "401", description = "인증 실패")
//        })
//        @PutMapping("/password")
//        public ResponseEntity<Map<String, String>> changePassword(
//            @Valid @RequestBody PasswordChangeDTO passwordChangeDTO) {
//
//            UserDTO currentUser = sessionService.getUserFromToken();
//            if (currentUser == null) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//            }
//
//            log.info("비밀번호 변경 요청 - userUuid: {}", currentUser.getUserUuid());
//
//            userService.changePassword(currentUser.getUserUuid(), passwordChangeDTO);
//
//            Map<String, String> response = new HashMap<>();
//            response.put("status", "success");
//            response.put("message", "비밀번호가 성공적으로 변경되었습니다.");
//
//            return ResponseEntity.ok(response);

}
