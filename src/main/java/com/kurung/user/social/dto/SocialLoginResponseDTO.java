package com.kurung.user.social.dto;

import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "소셜 로그인 응답 DTO")
public class SocialLoginResponseDTO {
    @Schema(description = "응답 메시지", example = "카카오 로그인 성공")
    private String message;

    @Schema(description = "회원 정보")
    private UserDTO userInfo;

    @Schema(description = "신규 회원 여부", example = "true")
    private boolean isNewUser;
} 