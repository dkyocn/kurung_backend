package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import java.time.LocalDateTime;

public interface UserRepositorySupport {

    // 기존 메서드들
    UserEntity getUserByUuid(String userUuid);
    UserEntity getByUserId(String userId);
    boolean existsByUserId(String userId);
    boolean existsByUserNick(String userNick);
    long countByUuidStartingWith(String prefix);
    UserEntity findByUserPathAndUserKey(UserPath userPath, String userKey);
    void updatePasswordByEmail(String email, String newPassword);
    void updateRefreshToken(String userUuid, String refreshToken);

    // 프로필 관련 메서드 추가
    boolean checkNicknameAvailability(String userNick);
    void updateUserProfile(String userUuid, String userNick, LocalDateTime userAge, Gender userGender, String profileImg);
}