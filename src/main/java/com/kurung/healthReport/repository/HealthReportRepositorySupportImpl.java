package com.kurung.healthReport.repository;

import static com.kurung.healthReport.entity.QHealthReportEntity.healthReportEntity;

import com.kurung.healthReport.entity.HealthReportEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.sql.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HealthReportRepositorySupportImpl implements HealthReportRepositorySupport{

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public HealthReportEntity findByReportMonth(Date reportMonth){
    return jpaQueryFactory.selectFrom(healthReportEntity)
        .where(healthReportEntity.reportMonth.eq(reportMonth))
        .fetchOne();
  }

}
