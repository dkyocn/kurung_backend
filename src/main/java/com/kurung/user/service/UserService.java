package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import com.kurung.user.social.dto.SocialUserInfo;
import com.kurung.user.social.dto.SocialLoginResponseDTO;
import com.kurung.user.dto.UserSignupResponseDTO;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    boolean registerUser(UserEntity user);
    
    // 회원가입 응답 DTO 반환 메서드
    UserSignupResponseDTO registerUserWithResponse(UserDTO userDTO);
    
    // 소셜 로그인 관련 메서드 추가
    UserDTO socialLogin(String socialToken, UserPath userPath);
    boolean socialSignup(SocialUserInfo socialUserInfo);
    UserDTO getUserBySocialInfo(UserPath userPath, String userKey);
    // 소셜 로그인 테스트용 응답 DTO 반환 메서드
    SocialLoginResponseDTO testSocialLogin(UserPath userPath, String socialUserId);
    // 소셜 로그인 응답 DTO 반환 메서드 (실제 소셜 API 연동용)
    SocialLoginResponseDTO socialLoginWithResponse(String socialToken, UserPath userPath);
}


