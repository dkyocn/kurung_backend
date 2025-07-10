package com.kurung.user.repository;

import com.kurung.user.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, UserRepositorySupport {
  Optional<UserEntity> findByUserUuid(String userUuid);

}

