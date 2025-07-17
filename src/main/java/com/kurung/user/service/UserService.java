package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);

    UserDTO getUserByUserId(String userId);

    void updateRefresh(UserDTO userDTO, String refreshToken);
}