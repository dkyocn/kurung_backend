package com.kurung.diagnosis.repository;

import static com.kurung.diagnosis.entity.QHealthDiagnosisEntity.healthDiagnosisEntity;

import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DiagnosisRepositorySupportImpl implements DiagnosisRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public HealthDiagnosisEntity getDiagnosisByUserUuid(String userUuid) {
    return jpaQueryFactory.selectFrom(healthDiagnosisEntity)
        .where(healthDiagnosisEntity.user.userUuid.eq(userUuid))
        .fetchOne();
  }
}
