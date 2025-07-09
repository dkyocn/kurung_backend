package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QSupplementsEntity.supplementsEntity;

import com.kurung.medicine.entity.SupplementsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MedicineRepositorySupportImpl implements MedicineRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  public SupplementsEntity getSuppById(int substanceId) {
    return jpaQueryFactory.selectFrom(supplementsEntity)
        .where(supplementsEntity.suppId.eq(substanceId))
        .fetchOne();
  }
}
