package com.kurung.medicine.repository;

import com.kurung.medicine.entity.InputMedicineEntity;
import java.util.List;

public interface InputMedicineRepositorySupport {
  List<InputMedicineEntity> getInputMediById(int mediInterId);
}
