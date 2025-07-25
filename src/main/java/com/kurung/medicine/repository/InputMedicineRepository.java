package com.kurung.medicine.repository;

import com.kurung.medicine.entity.InputMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputMedicineRepository extends JpaRepository<InputMedicineEntity,Integer>, InputMedicineRepositorySupport {

}
