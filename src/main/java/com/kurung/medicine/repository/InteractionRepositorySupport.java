package com.kurung.medicine.repository;

import com.kurung.medicine.entity.MedicineInteractionEntity;
import java.util.List;

public interface InteractionRepositorySupport {
  List<MedicineInteractionEntity> getResultByUser(String userUuid);
}
