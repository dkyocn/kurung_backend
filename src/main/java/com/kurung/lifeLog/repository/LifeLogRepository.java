package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeLogRepository extends JpaRepository<LifeLogEntity, Integer>, LifeLogRepositorySupport{

  String user(UserEntity user);
}
