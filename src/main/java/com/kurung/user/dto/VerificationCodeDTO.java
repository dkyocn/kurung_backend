package com.kurung.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "인증번호 DTO")
public class VerificationCodeDTO {
    
    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;
    
    @Schema(description = "인증번호 (확인 시에만 사용)", example = "123456")
    private String verificationCode;
    
    @Schema(description = "새 비밀번호 (재설정 시에만 사용)", example = "newPassword123")
    private String newPassword;
    
    @Schema(description = "새 비밀번호 확인 (재설정 시에만 사용)", example = "newPassword123")
    private String confirmPassword;
} 