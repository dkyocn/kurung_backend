package com.kurung.user.repository;

import com.kurung.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshRepository extends JpaRepository<RefreshToken, String> {
    void deleteByUserId(String userId);
}
