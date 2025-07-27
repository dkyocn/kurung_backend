package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.PasswordResetRequestDTO;
import com.kurung.user.dto.PasswordResetResponseDTO;
import com.kurung.user.dto.VerificationCodeDTO;
import com.kurung.common.dto.ApiResponseDTO;
import com.kurung.user.entity.UserEntity;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    void registerUser(UserDTO userDTO);
    
    // 비밀번호 재설정 메서드
    PasswordResetResponseDTO resetPassword(String userUuid, PasswordResetRequestDTO request);
    
    // 비밀번호 재설정 메서드 (VerificationCodeDTO 사용)
    PasswordResetResponseDTO resetPassword(String userUuid, VerificationCodeDTO request);
    
    // 인증번호 발송 메서드
    ApiResponseDTO<String> sendVerificationCode(VerificationCodeDTO request);
    
    // 인증번호 확인 메서드
    ApiResponseDTO<String> confirmVerificationCode(VerificationCodeDTO request);
    
    // 이메일로 비밀번호 재설정 메서드
    ApiResponseDTO<String> resetPasswordByEmail(VerificationCodeDTO request);
}


