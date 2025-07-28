package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    //Uuid를 기준으로 사용자 정보 조회
    @Override
    public UserEntity getUserByUuid(String userUuid) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userUuid.eq(userUuid))
            .fetchOne();
    }

    //Id를 사용자 정보 조회
    @Override
    public UserEntity getByUserId(String userId) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userId.eq(userId))
            .fetchOne();
    }

    //UserId가 db에 존재하는지 확인
    @Override
    public boolean existsByUserId(String userId) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userId.eq(userId))
            .fetchCount() > 0;
    }

    //가입한 날짜 + 순번 형식의 UUID
    @Override
    public long countByUuidStartingWith(String datePrefix) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userUuid.startsWith(datePrefix))
            .fetchCount();
    }

    // 소셜 로그인 관련 메서드 구현
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
            .selectFrom(userEntity)
            .where(userEntity.userPath.eq(userPath)
                .and(userEntity.userKey.eq(userKey)))
            .fetchCount() > 0;
    }
}