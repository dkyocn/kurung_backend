package com.kurung.exercise.repository;

import com.kurung.exercise.entity.RoutinesEntity;
import java.time.LocalDateTime;
import java.util.List;

public interface RoutinesRepositorySupport {

  RoutinesEntity getRoutinesById(int id);

  List<RoutinesEntity> findRoutinesByUser(String userUuid, LocalDateTime start,
      LocalDateTime end);
}
