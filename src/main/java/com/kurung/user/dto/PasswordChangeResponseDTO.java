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
@Schema(description = "비밀번호 변경 응답 DTO")
public class PasswordChangeResponseDTO {
    
    @Schema(description = "응답 메시지", example = "비밀번호가 성공적으로 변경되었습니다.")
    private String message;
    
    @Schema(description = "성공 여부", example = "true")
    private boolean success;
} 