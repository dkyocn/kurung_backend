package com.kurung.medicine.repository;

import com.kurung.medicine.entity.SupplementsEntity;

public interface MedicineRepositorySupport {
  SupplementsEntity getSuppById(int substanceId);
}
