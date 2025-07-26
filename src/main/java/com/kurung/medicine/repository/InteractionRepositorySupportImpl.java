package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QMedicineInteractionEntity.medicineInteractionEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InteractionRepositorySupportImpl implements InteractionRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;
}
