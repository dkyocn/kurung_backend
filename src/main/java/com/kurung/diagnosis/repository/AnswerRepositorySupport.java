package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthAnswerEntity;
import java.util.List;

public interface AnswerRepositorySupport {

  List<HealthAnswerEntity> getByUserUuid(String userUuid);
}
