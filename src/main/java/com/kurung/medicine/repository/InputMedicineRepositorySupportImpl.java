package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QInputMedicineEntity.inputMedicineEntity;
import static com.kurung.medicine.entity.QMedicineInteractionEntity.medicineInteractionEntity;
import static com.kurung.user.entity.QUserEntity.userEntity;

import com.kurung.medicine.entity.InputMedicineEntity;
import com.kurung.medicine.entity.MedicineInteractionEntity;
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

  @Override
  public List<InputMedicineEntity> getByUserUuid(String userUuid) {
    return jpaQueryFactory
        .selectFrom(inputMedicineEntity)
        .join(inputMedicineEntity.medicineInteraction, medicineInteractionEntity)
        .join(medicineInteractionEntity.user, userEntity)
        .where(userEntity.userUuid.eq(userUuid))
        .fetch();
  }


  @Override
  public MedicineInteractionEntity getInteractionByMediIds(int id1, int id2) {
    InputMedicineEntity result = jpaQueryFactory
        .selectFrom(inputMedicineEntity)
        .join(inputMedicineEntity.medicineInteraction, medicineInteractionEntity).fetchJoin()
        .where(
            (inputMedicineEntity.medicine1.mediId.eq((int) id1)
                .and(inputMedicineEntity.medicine2.mediId.eq((int) id2)))
                .or(
                    inputMedicineEntity.medicine1.mediId.eq((int) id2)
                        .and(inputMedicineEntity.medicine2.mediId.eq((int) id1))
                )
        )
        .fetchOne();

    return result.getMedicineInteraction();
  }
}
