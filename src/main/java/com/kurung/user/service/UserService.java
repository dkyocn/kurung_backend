package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
    UserEntity login(UserEntity userEntity);
}
