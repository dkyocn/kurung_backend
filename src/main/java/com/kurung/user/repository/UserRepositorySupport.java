package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;  // ✅ 꼭 필요한 import

public interface UserRepositorySupport {
    UserEntity getUserByUuid(String userUuid);
}

