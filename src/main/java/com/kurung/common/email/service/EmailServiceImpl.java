package com.kurung.common.email.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  // 이메일별 인증 코드 저장소 (멀티 사용자 대응)
  private final Map<String, String> emailCodeMap = new ConcurrentHashMap<>();

  // 인증 코드 생성
  private String createVerificationCode() {
    Random random = new Random();
    StringBuilder code = new StringBuilder();

    for (int i = 0; i < 6; i++) {
      code.append(random.nextInt(10));
    }

    return code.toString();
  }

  @Override
  public void sendVerificationEmail(String toEmail) throws MessagingException {
    log.info("이메일 전송 시작 - 수신자: {}", toEmail);
    
    // 이메일 주소 유효성 검사
    if (toEmail == null || toEmail.trim().isEmpty()) {
      throw new IllegalArgumentException("이메일 주소가 비어있습니다.");
    }
    
    // 기본적인 이메일 형식 검사
    if (!toEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
      throw new IllegalArgumentException("유효하지 않은 이메일 형식입니다: " + toEmail);
    }
    
    try {
      String code = createVerificationCode();
      emailCodeMap.put(toEmail, code);
      log.info("인증 코드 생성 완료 - 이메일: {}, 코드: {}", toEmail, code);

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

      log.info("MimeMessage 생성 완료, 전송 시작...");
      mailSender.send(message);
      log.info("이메일 전송 성공! - 수신자: {}, 인증 코드: {}", toEmail, code);
      
    } catch (MessagingException e) {
      log.error("이메일 전송 실패 - 수신자: {}, 오류: {}", toEmail, e.getMessage(), e);
      throw e;
    } catch (Exception e) {
      log.error("예상치 못한 오류 - 수신자: {}, 오류: {}", toEmail, e.getMessage(), e);
      throw new MessagingException("이메일 전송 중 오류 발생", e);
    }
  }

  @Override
  public boolean verifyCode(String email, String inputCode) {
    String savedCode = emailCodeMap.get(email);
    return inputCode != null && inputCode.equals(savedCode);
  }
}
