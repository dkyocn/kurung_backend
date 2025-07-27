package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.PasswordChangeRequestDTO;
import com.kurung.user.dto.PasswordChangeResponseDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.common.dto.ApiResponseDTO;
import com.kurung.user.entity.UserEntity;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    void registerUser(UserDTO userDTO);
    
    // 비밀번호 변경 메서드
    PasswordChangeResponseDTO changePassword(String userUuid, PasswordChangeRequestDTO request);
    
    // 비밀번호 재설정 메서드
    PasswordChangeResponseDTO resetPassword(String userUuid, VerificationCodeDTO request);
    
    // 인증번호 발송 메서드
    ApiResponseDTO<String> sendVerificationCode(VerificationCodeDTO request);
    
    // 인증번호 확인 메서드
    ApiResponseDTO<String> confirmVerificationCode(VerificationCodeDTO request);
    
    // 이메일로 비밀번호 재설정 메서드
    ApiResponseDTO<String> resetPasswordByEmail(VerificationCodeDTO request);
}


