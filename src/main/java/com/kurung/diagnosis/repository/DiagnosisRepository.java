package com.kurung.diagnosis.repository;

import com.kurung.diagnosis.entity.HealthDiagnosisEntity;
import com.kurung.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<HealthDiagnosisEntity,Integer>, DiagnosisRepositorySupport {

}
