package com.kurung.lifeLog.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.repository.LifeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifeLogServiceImpl implements LifeLogService{

  private final LifeLogRepository lifeLogRepository;

  @Override
  public LifeLogDTO getLifeLogById(int id) {
    return LifeLogDTO.toLifeLogBuilder()
        .lifeLogEntity(lifeLogRepository.findById(id)
            .orElseThrow(() -> new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND)))
        .build();
  }

}
