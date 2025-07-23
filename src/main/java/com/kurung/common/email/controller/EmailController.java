package com.kurung.common.email.controller;

import com.kurung.common.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/user")
@RequiredArgsConstructor
public class EmailController {

  private final EmailService emailService;

  // 이메일로 인증 코드 전송
  @PostMapping("/send-verification-email")
  public ResponseEntity<String> sendVerificationEmail(@RequestParam String email) {
    log.info("이메일 인증 요청 - 수신 이메일: {}", email);

    if (email == null || email.trim().isEmpty()) {
      return ResponseEntity.badRequest().body("이메일 주소를 입력해주세요.");
    }

    try {
      emailService.sendVerificationEmail(email);
      return ResponseEntity.ok("인증 코드가 이메일로 전송되었습니다.");
    } catch (MessagingException e) {
      log.error("이메일 전송 실패 - 이메일: {}, 오류: {}", email, e.getMessage(), e);
      return ResponseEntity.internalServerError().body("이메일 전송에 실패했습니다. 잠시 후 다시 시도해주세요.");
    } catch (Exception e) {
      log.error("예상치 못한 오류 발생 - 이메일: {}, 오류: {}", email, e.getMessage(), e);
      return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
    }
  }

  // 인증 코드 검증
  @PostMapping("/verify-code")
  public ResponseEntity<String> verifyCode(
      @RequestParam String email,
      @RequestParam String code) {

    boolean verified = emailService.verifyCode(email, code);

    if (verified) {
      return ResponseEntity.ok("인증 성공");
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body("인증 실패 - 코드가 일치하지 않거나 만료되었습니다.");
    }
  }
}
