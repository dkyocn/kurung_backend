package com.kurung.medicine.repository;

import com.kurung.medicine.entity.RecommendedSupplementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecSupplementsRepository extends JpaRepository<RecommendedSupplementsEntity,Integer>, RecSupplementsRepositorySupport {

}
