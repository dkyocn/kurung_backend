package com.kurung.medicine.repository;

import com.kurung.medicine.entity.SupplementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<SupplementsEntity, Integer>, MedicineRepositorySupport {

}
