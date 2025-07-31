package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.Gender;
import com.kurung.user.enumeration.UserPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserEntity getUserByUuid(String userUuid) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userUuid.eq(userUuid))
            .fetchOne();
    }

    @Override
    public UserEntity getByUserId(String userid) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userId.eq(userid))
            .fetchOne();
    }

    @Override
    public boolean existsByUserId(String userid) {
        return jpaQueryFactory
            .selectOne()
            .from(userEntity)
            .where(userEntity.userId.eq(userid))
            .fetchFirst() != null;
    }

    @Override
    public boolean existsByUserNick(String userNick) {
        return jpaQueryFactory
            .selectOne()
            .from(userEntity)
            .where(userEntity.userNick.eq(userNick))
            .fetchFirst() != null;
    }

    @Override
    public long countByUuidStartingWith(String datePrefix) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userUuid.startsWith(datePrefix))
            .fetchCount();
    }

    @Override
    public UserEntity findByUserPathAndUserKey(UserPath userPath, String userKey) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userPath.eq(userPath)
                .and(userEntity.userKey.eq(userKey)))
            .fetchOne();
    }

    @Override
    @Transactional
    public void updatePasswordByEmail(String email, String newPassword) {
        jpaQueryFactory
            .update(userEntity)
            .set(userEntity.userPwd, newPassword)
            .where(userEntity.userId.eq(email))
            .execute();
    }

    @Override
    @Transactional
    public void updateRefreshToken(String userUuid, String refreshToken) {
        jpaQueryFactory
            .update(userEntity)
            .set(userEntity.userRefreshToken, refreshToken)
            .where(userEntity.userUuid.eq(userUuid))
            .execute();
    }

    @Override
    public boolean checkNicknameAvailability(String userNick) {
        return !existsByUserNick(userNick);
    }

    @Override
    @Transactional
    public void updateUserProfile(String userUuid, String userNick, LocalDateTime userAge, Gender userGender, String profileImg) {
        jpaQueryFactory
            .update(userEntity)
            .set(userEntity.userNick, userNick)
            .set(userEntity.userAge, userAge)
            .set(userEntity.userGender, userGender)
            .set(userEntity.profileImg, profileImg)
            .where(userEntity.userUuid.eq(userUuid))
            .execute();
    }
}