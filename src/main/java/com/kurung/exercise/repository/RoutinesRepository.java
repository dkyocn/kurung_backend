package com.kurung.exercise.repository;

import com.kurung.exercise.entity.RoutinesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutinesRepository extends JpaRepository<RoutinesEntity, Integer>, RoutinesRepositorySupport {
}

