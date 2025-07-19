package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;  // ✅ 엔티티 import
import com.querydsl.jpa.impl.JPAQueryFactory;  // ✅ QueryDSL용 쿼리팩토리 import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserEntity getUserByUuid(String userUuid) {
        return jpaQueryFactory.selectFrom(userEntity)
                .where(userEntity.userUuid.eq(userUuid))
                .fetchOne();
    }

    @Override
    // 사용자 아이디로 조회
    public UserEntity getByUserId(String userid) {
        return jpaQueryFactory
                .selectFrom(userEntity)
                .where(userEntity.userId.eq(userid))
                .fetchOne();
    }
}

