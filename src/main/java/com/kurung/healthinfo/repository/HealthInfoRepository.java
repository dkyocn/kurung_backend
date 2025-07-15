package com.kurung.healthinfo.repository;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthInfoRepository extends JpaRepository<HealthInfoEntity , Integer>, HealthInfoRepositorySupport {
}
