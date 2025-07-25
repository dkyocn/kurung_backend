package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<HealthAnswerEntity, Integer>, AnswerRepositorySupport {

}
