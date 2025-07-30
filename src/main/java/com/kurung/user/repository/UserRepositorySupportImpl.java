package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean existsByUserPathAndUserKey(UserPath userPath, String userKey) {
        return jpaQueryFactory
            .selectOne()
            .from(userEntity)
            .where(userEntity.userPath.eq(userPath)
                .and(userEntity.userKey.eq(userKey)))
            .fetchFirst() != null;
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
}