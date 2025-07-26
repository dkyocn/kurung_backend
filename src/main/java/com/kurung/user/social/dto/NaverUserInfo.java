package com.kurung.user.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "네이버 사용자 정보 DTO")
public class NaverUserInfo {
    
    @JsonProperty("resultcode")
    @Schema(description = "API 호출 결과 코드", example = "00")
    private String resultcode;
    
    @JsonProperty("message")
    @Schema(description = "API 호출 결과 메시지", example = "success")
    private String message;
    
    @JsonProperty("response")
    @Schema(description = "네이버 사용자 정보")
    private NaverResponse response;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NaverResponse {
        @JsonProperty("id")
        @Schema(description = "네이버 사용자 고유 ID", example = "123456789")
        private String id;
        
        @JsonProperty("email")
        @Schema(description = "사용자 이메일", example = "user@example.com")
        private String email;
        
        @JsonProperty("nickname")
        @Schema(description = "사용자 닉네임", example = "홍길동")
        private String nickname;
        
        @JsonProperty("name")
        @Schema(description = "사용자 이름", example = "홍길동")
        private String name;
        
        @JsonProperty("gender")
        @Schema(description = "성별", example = "M")
        private String gender;
        
        @JsonProperty("age")
        @Schema(description = "나이", example = "20-29")
        private String age;
        
        @JsonProperty("birthday")
        @Schema(description = "생일", example = "01-01")
        private String birthday;
        
        @JsonProperty("profile_image")
        @Schema(description = "프로필 이미지 URL", example = "https://example.com/profile.jpg")
        private String profileImage;
        
        @JsonProperty("mobile")
        @Schema(description = "휴대폰 번호", example = "010-1234-5678")
        private String mobile;
    }
}
