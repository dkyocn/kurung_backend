package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QInputMedicineEntity.inputMedicineEntity;
import static com.kurung.medicine.entity.QMedicineInteractionEntity.medicineInteractionEntity;

import com.kurung.medicine.entity.InputMedicineEntity;
import com.kurung.medicine.entity.QInputMedicineEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InputMedicineRepositorySupportImpl implements InputMedicineRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<InputMedicineEntity> getInputMediById(int mediInterId){
    return jpaQueryFactory.selectFrom(inputMedicineEntity)
        .join(inputMedicineEntity.medicine1).fetchJoin()
        .join(inputMedicineEntity.medicine2).fetchJoin()
        .join(inputMedicineEntity.medicineInteraction).fetchJoin()
        .where(inputMedicineEntity.medicineInteraction.mediInterId.eq(mediInterId))
        .fetch();
  }
}
