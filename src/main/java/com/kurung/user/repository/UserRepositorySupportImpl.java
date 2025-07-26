package com.kurung.user.repository;

import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.user.entity.UserEntity;
import com.kurung.user.enumeration.UserPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    @PersistenceContext
    private final EntityManager em;
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
        String jpql = "SELECT COUNT(u) > 0 FROM TB_USER u WHERE u.userId = :userId";
        Boolean exists = em.createQuery(jpql, Boolean.class)
            .setParameter("userId", userId)
            .getSingleResult();
        return exists;
    }

    //가입한 날짜 + 순번 형식의 UUID
    @Override
    public long countByUuidStartingWith(String datePrefix) {
        String jpql = "SELECT COUNT(u) FROM TB_USER u WHERE u.userUuid LIKE :prefix";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("prefix", datePrefix + "%");
        return query.getSingleResult();
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
        String jpql = "SELECT COUNT(u) > 0 FROM TB_USER u WHERE u.userPath = :userPath AND u.userKey = :userKey";
        Boolean exists = em.createQuery(jpql, Boolean.class)
            .setParameter("userPath", userPath)
            .setParameter("userKey", userKey)
            .getSingleResult();
        return exists;
    }
}
