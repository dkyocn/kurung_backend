package com.kurung.user.auth.controller;

import com.kurung.user.auth.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kurung.user.auth.dto.EmailRequestDTO;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/kurung/auth")
@RequiredArgsConstructor
public class AuthController {

  private final EmailService emailService;

  // 이메일로 인증 코드 전송
  @PostMapping("/send-verification-email")
  public ResponseEntity<Map<String, Object>> sendVerificationEmail(@RequestParam String email) {
    Map<String, Object> response = new HashMap<>();
    try {
      emailService.sendVerificationEmail(email);
      response.put("success", true);
      response.put("message", "인증 코드가 이메일로 전송되었습니다.");
      return ResponseEntity.ok(response);
    } catch (MessagingException e) {
      log.error("이메일 전송 실패", e);
      response.put("success", false);
      response.put("message", "이메일 전송에 실패했습니다.");
      return ResponseEntity.internalServerError().body(response);
    }
  }

  // 인증 코드 검증
  @PostMapping("/verify-code")
  public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody EmailRequestDTO request) {
    Map<String, Object> response = new HashMap<>();
    boolean verified = emailService.verifyCode(request.getEmail(), request.getCode());
    response.put("verified", verified);
    response.put("message", verified ? "인증 성공" : "인증 실패 - 코드가 일치하지 않거나 만료되었습니다.");
    return ResponseEntity.ok(response);
  }
}
