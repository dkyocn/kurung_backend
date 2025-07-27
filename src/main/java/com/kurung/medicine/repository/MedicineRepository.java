package com.kurung.medicine.repository;

import com.kurung.medicine.entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<MedicineEntity, Integer>, MedicineRepositorySupport {
}
