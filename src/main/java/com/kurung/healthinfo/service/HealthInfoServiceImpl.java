package com.kurung.healthinfo.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.kurung.healthinfo.repository.HealthInfoRepository;
import com.kurung.user.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public List<HealthInfoDTO> getHealthInfoMonthList(LocalDate currentDate, String userUuid) {

    // 사용자 존재 여부 확인
    userService.getUserByUuid(userUuid);

    // 월별 건강 정보 목록 조회
    List<HealthInfoEntity> healthInfoMonthList = healthInfoRepository.getHealthInfoMonthList(
        currentDate.withDayOfMonth(1).atStartOfDay(),
        currentDate.withDayOfMonth(currentDate.lengthOfMonth()).atTime(23, 59, 59),
        userUuid);

    //  DTO로 변환하여 반환
    return healthInfoMonthList.stream()
        .map(entity -> HealthInfoDTO.toHealthInfoBuilder().entity(entity).build())
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public HealthInfoDTO updateHealthInfo(HealthInfoDTO healthInfoDTO) {

    HealthInfoEntity entity = healthInfoRepository.getHealthInfoById(healthInfoDTO.getHealthinfoId());

    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.HEALTH_INFO_NOT_FOUND);
    }

    try {
      entity.updateHealthInfo(healthInfoDTO);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.HEALTH_INFO_UPDATE_ERROR);
    }
    return healthInfoDTO;
  }
}

