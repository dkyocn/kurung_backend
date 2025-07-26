package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.dto.PasswordChangeRequestDTO;
import com.kurung.user.dto.PasswordChangeResponseDTO;
import com.kurung.user.entity.UserEntity;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    boolean registerUser(UserEntity user);
    
    // 비밀번호 변경 메서드
    PasswordChangeResponseDTO changePassword(String userUuid, PasswordChangeRequestDTO request);
}


