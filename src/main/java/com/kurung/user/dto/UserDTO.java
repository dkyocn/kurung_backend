package com.kurung.user.dto;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    protected String userUuid;
    protected String userId;
    protected boolean userFaceLoginYN;
    protected String userFaceLoginRef;
    protected String userPwd;
    protected String userNick;
    protected Gender userGender;
    protected Date userAge;
    protected String userKey;
    protected UserPath userPath;
    protected String profileImg;
    protected boolean isActive;
    protected boolean adminYN;

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
