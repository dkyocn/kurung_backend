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
@Schema(description = "비밀번호 재설정 DTO")
public class PasswordResetDTO {
    
    @Schema(description = "현재 비밀번호", example = "currentPassword123")
    private String currentPassword;
    @Schema(description = "새 비밀번호", example = "newPassword123")
    private String newPassword;
    @Schema(description = "새 비밀번호 확인", example = "newPassword123")
    private String confirmPassword;
    @Schema(description = "응답 메시지", example = "비밀번호가 성공적으로 재설정되었습니다.")
    private String message;
    @Schema(description = "성공 여부", example = "true")
    private boolean success;
} 