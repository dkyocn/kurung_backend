package com.kurung.user.social.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "카카오 사용자 정보 DTO")
public class KakaoUserInfo {

    @JsonProperty("id")
    @Schema(description = "카카오 사용자 고유 ID", example = "123456789")
    private Long id;

    @JsonProperty("connected_at")
    @Schema(description = "카카오 계정 연결 시간")
    private String connectedAt;

    @JsonProperty("properties")
    @Schema(description = "카카오 사용자 속성 정보")
    private KakaoProperties properties;

    @JsonProperty("kakao_account")
    @Schema(description = "카카오 계정 정보")
    private KakaoAccount kakaoAccount;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoProperties {
        @JsonProperty("nickname")
        private String nickname;

        @JsonProperty("profile_image")
        private String profileImage;

        @JsonProperty("thumbnail_image")
        private String thumbnailImage;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoAccount {
        @JsonProperty("email")
        private String email;

        @JsonProperty("email_needs_agreement")
        private Boolean emailNeedsAgreement;

        @JsonProperty("is_email_valid")
        private Boolean isEmailValid;

        @JsonProperty("is_email_verified")
        private Boolean isEmailVerified;

        @JsonProperty("age_range")
        private String ageRange;

        @JsonProperty("birthday")
        private String birthday;

        @JsonProperty("gender")
        private String gender;
    }
}
