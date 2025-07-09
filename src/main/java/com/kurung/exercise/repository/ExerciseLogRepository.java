package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ExerciseLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLogEntity, Integer>, ExerciseLogRepositorySupport {
    // 기본 CRUD + Support 기능도 확장
}
