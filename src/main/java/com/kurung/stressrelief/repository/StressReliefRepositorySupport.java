package com.kurung.stressrelief.repository;

import com.kurung.stressrelief.entity.StressReliefEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StressReliefRepositorySupport {

    StressReliefEntity getStressReliefById(int id);

    Page<StressReliefEntity> getStressReliefByPage(Pageable pageable, String keyword);
}










