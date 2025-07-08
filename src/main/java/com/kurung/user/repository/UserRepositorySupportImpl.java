package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;  // ✅ 엔티티 import
import com.querydsl.jpa.impl.JPAQueryFactory;  // ✅ QueryDSL용 쿼리팩토리 import
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.kurung.user.entity.QUserEntity.userEntity;  // ✅ QueryDSL에서 자동 생성된 Q 클래스 import

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
}

