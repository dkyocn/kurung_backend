package com.kurung.user.social.dto;

import com.kurung.user.enumeration.Gender;
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
@Schema(description = "소셜 사용자 정보 DTO")
public class SocialUserInfo {
    
    @Schema(description = "소셜 사용자 고유 ID", example = "123456789")
    private String socialUserId;
    
    @Schema(description = "사용자 이메일", example = "user@example.com")
    private String email;
    
    @Schema(description = "사용자 닉네임", example = "홍길동")
    private String nickname;
    
    @Schema(description = "사용자 성별", example = "MALE")
    private Gender gender;
    
    @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
    private String profileImage;
    
    @Schema(description = "소셜 로그인 경로", example = "KAKAO")
    private UserPath userPath;
    
    @Schema(description = "생년월일", example = "1990-01-01")
    private String birthDate;
}
