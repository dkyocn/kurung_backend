package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    boolean registerUser(UserEntity user);

    // 회원가입 응답 DTO 반환 메서드 (UserDTO로 통합)
    UserDTO registerUserWithResponse(UserDTO userDTO);

    // 소셜 로그인 관련 메서드 추가 (UserDTO로 통합)
    UserDTO socialLogin(String socialToken, UserPath userPath);
    boolean socialSignup(UserDTO socialUserInfo);
    UserDTO getUserBySocialInfo(UserPath userPath, String userKey);

    // 소셜 로그인 응답 DTO 반환 메서드 (실제 소셜 API 연동용)
    UserDTO socialLoginWithResponse(String socialToken, UserPath userPath);

    // 비밀번호 재설정 관련 메서드 (UserDTO로 통합)
    void sendVerificationCode(UserDTO request);
    boolean confirmVerificationCode(UserDTO request);
    UserDTO resetPasswordByEmail(UserDTO request);
}


