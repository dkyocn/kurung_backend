package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<HealthOptionEntity, Integer>, OptionRepositorySupport {

}
