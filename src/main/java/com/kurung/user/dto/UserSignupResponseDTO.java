package com.kurung.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "회원가입 응답 DTO")
public class UserSignupResponseDTO {
    @Schema(description = "응답 메시지", example = "회원가입이 성공적으로 완료되었습니다.")
    private String message;
    
    @Schema(description = "성공 여부", example = "true")
    private boolean success;
} 