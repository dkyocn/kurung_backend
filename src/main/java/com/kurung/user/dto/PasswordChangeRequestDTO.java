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
@Schema(description = "비밀번호 변경 요청 DTO")
public class PasswordChangeRequestDTO {
    
    @Schema(description = "현재 비밀번호", example = "currentPassword123")
    private String currentPassword;
    
    @Schema(description = "새 비밀번호", example = "newPassword123")
    private String newPassword;
    
    @Schema(description = "새 비밀번호 확인", example = "newPassword123")
    private String confirmPassword;
} 