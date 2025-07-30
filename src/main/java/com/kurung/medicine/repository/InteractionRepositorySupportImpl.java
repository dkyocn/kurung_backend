package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QMedicineInteractionEntity.medicineInteractionEntity;

import com.kurung.medicine.entity.MedicineInteractionEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InteractionRepositorySupportImpl implements InteractionRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<MedicineInteractionEntity> getResultByUser(String userUuid){
    return jpaQueryFactory.selectFrom(medicineInteractionEntity)
        .where(medicineInteractionEntity.user.userUuid.eq(userUuid))
        .fetch();
  }

}
