package com.kurung.user.social.dto;

import com.kurung.user.enumeration.UserPath;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "소셜 로그인 요청 DTO")
public class SocialLoginRequest {
    
    @Schema(description = "소셜 인증 토큰", example = "kakao_access_token_here")
    private String socialToken;
    
    @Schema(description = "소셜 로그인 경로", example = "KAKAO")
    private UserPath userPath;
    
    @Schema(description = "소셜 사용자 고유 ID", example = "123456789")
    private String socialUserId;
}
