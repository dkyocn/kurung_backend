package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserDTO getUserByUserId(String userId);
    void updateRefresh(UserDTO userDTO, String refreshToken);
    boolean checkUserIdAvailability(String userId);
    boolean registerUser(UserEntity user);


}


