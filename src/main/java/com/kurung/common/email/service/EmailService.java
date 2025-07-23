package com.kurung.common.email.service;

import jakarta.mail.MessagingException;

public interface EmailService {
  void sendVerificationEmail(String toEmail) throws MessagingException;
  boolean verifyCode(String email, String inputCode);
}
