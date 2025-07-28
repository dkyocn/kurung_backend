package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;

public interface UserRepositorySupport {
    UserEntity getUserByUuid(String userUuid);
    UserEntity getByUserId(String userid);
    boolean existsByUserId(String userid);
    long countByUuidStartingWith(String datePrefix);

    // 소셜 로그인 관련 메서드 추가
    UserEntity findByUserPathAndUserKey(UserPath userPath, String userKey);
    boolean existsByUserPathAndUserKey(UserPath userPath, String userKey);
}

