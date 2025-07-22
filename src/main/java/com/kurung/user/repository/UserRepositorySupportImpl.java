package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    @PersistenceContext
    private final EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UserEntity getUserByUuid(String userUuid) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userUuid.eq(userUuid))
            .fetchOne();
    }

    @Override
    public UserEntity getByUserId(String userId) {
        return jpaQueryFactory
            .selectFrom(userEntity)
            .where(userEntity.userId.eq(userId))
            .fetchOne();
    }

    @Override
    public boolean existsByUserId(String userId) {
        String jpql = "SELECT COUNT(u) > 0 FROM TB_USER u WHERE u.userId = :userId";
        Boolean exists = em.createQuery(jpql, Boolean.class)
            .setParameter("userId", userId)
            .getSingleResult();
        return exists;
    }
}
