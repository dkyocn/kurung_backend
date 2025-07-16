package com.kurung.stressrelief.service;

import com.kurung.stressrelief.dto.StressReliefDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StressReliefService {

    StressReliefDTO getStressReliefById(int id);

    Page<StressReliefDTO> getStressReliefByPage(Pageable pageable, String keyword);
}
