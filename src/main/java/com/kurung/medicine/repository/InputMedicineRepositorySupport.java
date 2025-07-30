package com.kurung.medicine.repository;

import com.kurung.medicine.entity.InputMedicineEntity;
import com.kurung.medicine.entity.MedicineInteractionEntity;
import java.util.List;

public interface InputMedicineRepositorySupport {
  List<InputMedicineEntity> getInputMediById(int mediInterId);
  List<InputMedicineEntity> getByUserUuid(String userUuid);
  MedicineInteractionEntity getInteractionByMediIds(int id1, int id2);
}
