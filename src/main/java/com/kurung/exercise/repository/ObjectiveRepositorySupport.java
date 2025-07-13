package com.kurung.exercise.repository;

import com.kurung.exercise.entity.ObjectiveEntity;

public interface ObjectiveRepositorySupport {
    ObjectiveEntity findByObjectiveId(int id);

}
