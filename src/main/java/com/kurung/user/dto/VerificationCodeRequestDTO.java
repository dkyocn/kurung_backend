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
@Schema(description = "인증번호 발송 요청 DTO")
public class VerificationCodeRequestDTO {
    
    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;
} 