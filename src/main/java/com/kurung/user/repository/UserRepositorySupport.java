package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositorySupport {
    UserEntity getUserByUuid(String userUuid);
    UserEntity getByUserId(String userid);
    boolean existsByUserId(String userid);
    long countByUuidStartingWith(String datePrefix);


    // 소셜 로그인 관련 메서드 추가
    UserEntity findByUserPathAndUserKey(UserPath userPath, String userKey);
    boolean existsByUserPathAndUserKey(UserPath userPath, String userKey);

    // 비밀번호만 업데이트하는 메서드 추가
    void updatePasswordByEmail(String email, String newPassword);
}