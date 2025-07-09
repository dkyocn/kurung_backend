package com.kurung.user.service;

import com.kurung.user.dto.UserDTO;

public interface UserService {

    UserDTO getUserByUuid(String userUuid);
}