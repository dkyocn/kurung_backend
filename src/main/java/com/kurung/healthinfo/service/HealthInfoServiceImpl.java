package com.kurung.healthinfo.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.kurung.healthinfo.repository.HealthInfoRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthInfoServiceImpl implements HealthInfoService {

  private final HealthInfoRepository healthInfoRepository;
  private final UserService userService;

  @Override
  public List<HealthInfoDTO> getHealthInfo() {
    return healthInfoRepository.findAll().stream()
        .map(HealthInfoDTO::new)  // 엔티티를 DTO로 변환
        .collect(Collectors.toList());
  }

  @Override
  public HealthInfoDTO getHealthInfoById(String userUuid, LocalDateTime targetDate) {

    userService.getUserByUuid(userUuid);

    LocalDateTime startOfDay = targetDate.toLocalDate().atStartOfDay(); // 00:00:00
    LocalDateTime endOfDay = targetDate.toLocalDate().atTime(23, 59, 59); // 23:59:59

    HealthInfoEntity entity = healthInfoRepository.findByUserAndDateBetween(userUuid, startOfDay, endOfDay);

    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.HEALTH_INFO_NOT_FOUND);
    }

    return HealthInfoDTO.toHealthInfoBuilder().entity(entity).build();
  }


}
