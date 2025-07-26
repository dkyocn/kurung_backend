package com.kurung.medicine.repository;

import com.kurung.medicine.entity.SupplementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplementsRepository extends JpaRepository<SupplementsEntity, Integer>, SupplementsRepositorySupport {
}
