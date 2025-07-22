package com.kurung.user.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;

  private String verificationCode;

  public String createVerificationCode() {
    Random random = new Random();
    StringBuilder code = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      code.append(random.nextInt(10)); // 0~9 숫자 생성
    }

    verificationCode = code.toString();
    return verificationCode;
  }

  /**
   * 인증 코드 포함 이메일 전송
   */
  public void sendVerificationEmail(String toEmail) throws MessagingException {
    String code = createVerificationCode();

    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

    helper.setTo(toEmail);
    helper.setSubject("[Kurung] 이메일 인증 코드입니다.");
    helper.setText(
        "<h2>안녕하세요!</h2>" +
            "<p>Kurung 서비스를 이용해 주셔서 감사합니다.</p>" +
            "<p>요청하신 인증 코드는 아래와 같습니다.</p>" +
            "<h1 style='color:blue'>" + code + "</h1>" +
            "<p>5분 이내에 인증을 완료해 주세요.</p>", true
    );
    helper.setFrom("kurunghelpdesk@gmail.com");

    mailSender.send(message);
    log.info("이메일 전송 완료 - 대상: {}, 인증 코드: {}", toEmail, code);
  }

  public boolean verifyCode(String inputCode) {
    return inputCode.equals(verificationCode);
  }
}
