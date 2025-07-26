package com.kurung.medicine.repository;

import com.kurung.medicine.entity.MedicineInteractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<MedicineInteractionEntity,Integer>, InteractionRepositorySupport {
}
