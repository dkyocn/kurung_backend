package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;

public interface UserRepositorySupport {
    UserEntity getUserByUuid(String userUuid);
    UserEntity getByUserId(String userid);
}

