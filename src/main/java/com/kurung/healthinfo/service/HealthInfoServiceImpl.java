package com.kurung.healthinfo.service;

import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.kurung.healthinfo.repository.HealthInfoRepository;
import com.kurung.healthinfo.repository.HealthInfoRepositorySupport;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthInfoServiceImpl implements HealthInfoService {

  private final HealthInfoRepository healthInfoRepository;

  @Override
  public List<HealthInfoDTO> getHealthInfo() {
    return healthInfoRepository.findAll().stream()
        .map(HealthInfoDTO::new)  // 엔티티를 DTO로 변환
        .collect(Collectors.toList());
  }

  @Override
  public HealthInfoDTO getHealthInfoById(String userUuid, LocalDateTime targetDate) {
    HealthInfoEntity entity = healthInfoRepository.findByUserAndDate(userUuid, targetDate);
    return entity != null ? HealthInfoDTO.toHealthInfoBuilder().entity(entity).build() : null;
  }


}
