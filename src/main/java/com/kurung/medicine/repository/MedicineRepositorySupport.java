package com.kurung.medicine.repository;


import com.kurung.medicine.entity.MedicineEntity;
import java.util.List;

public interface MedicineRepositorySupport {
  List<MedicineEntity> getMedicineList(String keyword);
}
