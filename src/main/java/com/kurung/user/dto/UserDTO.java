package com.kurung.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    @Schema(description = "사용자 UUID", example = "2025061401")
    protected String userUuid;
    @Schema(description = "사용자 아이디", example = "thdalstj0450@gmail.com")
    protected String userId;
    @Schema(description = "페이스 로그인 등록 여부", example = "false")
    protected boolean userFaceLoginYN;
    @Schema(description = "페이스 로그인 백터", example = "0.1234, -0.5678, 0.8912, -0.2345, 0.6789, ... ,-0.4321, 0.0987, -0.6543, 0.3210, -0.9876")
    protected String userFaceLoginRef;
    @Schema(description = "사용자 비밀번호", example = "SHA-256 암호화 비밀번호")
    protected String userPwd;
    @Schema(description = "사용자 닉네임",example = "송민서")
    protected String userNick;
    @Schema(description = "사용자 성별", example = "FEMALE")
    protected Gender userGender;
    @Schema(description = "사용자 생일", example = "1996-01-01")
    protected LocalDateTime userAge;
    @Schema(description = "소셜 로그인 키")
    protected String userKey;
    @Schema(description = "사용자 로그인 경로", example = "NORMAL")
    protected UserPath userPath;
    @Schema(description = "사용자 프로필 사진")
    protected String profileImg;
    @Schema(description = "사용자 활성화 여부", example = "true")
    protected boolean isActive;
    @Schema(description = "관리자 여부", example = "true")
    protected boolean adminYN;
    @Schema(description = "리프레시 토큰 발급", example = "리프레시 토큰")
    protected String userRefreshToken;

    @Builder(builderMethodName = "toUserBuilder", builderClassName = "toUserBuilder")
    public UserDTO(UserEntity userEntity) {
        this.userUuid = userEntity.getUserUuid();
        this.userId = userEntity.getUserId();
        this.userFaceLoginYN = userEntity.isUserFaceLoginYN();
        this.userFaceLoginRef = userEntity.getUserFaceLoginRef();
        this.userPwd = userEntity.getUserPwd();
        this.userNick = userEntity.getUserNick();
        this.userGender = userEntity.getUserGender();
        this.userAge = userEntity.getUserAge();
        this.userKey = userEntity.getUserKey();
        this.userPath = userEntity.getUserPath();
        this.profileImg = userEntity.getProfileImg();
        this.isActive = userEntity.isActive();
        this.adminYN = userEntity.isAdminYN();
    }
}
