package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QInputMedicineEntity.inputMedicineEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InputMedicineRepositorySupportImpl implements InputMedicineRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;
}
