package com.kurung.stressrelief.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.stressrelief.dto.StressReliefDTO;
import com.kurung.stressrelief.entity.StressReliefEntity;
import com.kurung.stressrelief.repository.StressReliefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StressReliefServiceImpl implements StressReliefService {

    private final StressReliefRepository stressReliefRepository;

    @Override
    public StressReliefDTO getStressReliefById(int id) {
        StressReliefEntity entity = stressReliefRepository.getStressReliefById(id);

        if (entity == null) {
            throw new CustomIllegalArgumentException(CustomHttpStatus.STRESSRELIEF_NOT_FOUND);
        }

        return StressReliefDTO.toStressReliefBuilder()
                .entity(entity)
                .build();
    }

    @Override
    public Page<StressReliefDTO> getStressReliefByPage(Pageable pageable, String keyword) {
        Page<StressReliefEntity> stressReliefByPage = stressReliefRepository.getStressReliefByPage(
            pageable, keyword);
        return stressReliefByPage.map(stressReliefEntity -> StressReliefDTO.toStressReliefBuilder()
            .entity(stressReliefEntity)
            .build());
    }
}
