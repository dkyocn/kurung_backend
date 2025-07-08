package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;           // 💡 사용자 도메인 엔티티
import org.springframework.data.jpa.repository.JpaRepository;  // 💡 JPA 기본 Repository
import org.springframework.stereotype.Repository;   // 💡 Repository 빈 등록

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositorySupport {

}

