package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.entity.DietScoreEntity;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;
  private final UserService userService;

  @Override
  public List<MissionsDTO> getMissionsList() {
    List<MissionsEntity> missionsById = missionsRepository.getMissionsById();

    if (missionsById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.MISSIONS_NOT_FOUND);
    }

    return missionsById.stream()
        .map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build())
        .collect(Collectors.toList());
  }

  @Override
  public List<MissionsDTO> getTodayMissions(String userUuid) {

    UserDTO userByUuid = userService.getUserByUuid(userUuid);

    LocalDate today = LocalDate.now();
    LocalDateTime startOfDay = today.atStartOfDay();         // 00:00:00
    LocalDate localDate = startOfDay.toLocalDate();

    List<MissionsEntity> missionList = missionsRepository.getMissionList(userUuid, localDate);

    return missionList.stream()
        .map(MissionsDTO::new)
        .collect(Collectors.toList());
  }


  @Override
  public List<MissionsDTO> getMissionMonthList(LocalDateTime currentDate, String userUuid) {

    // ✅ 명시적으로 6월 1일 ~ 6월 30일 설정
    LocalDate startDate = LocalDate.of(2025, 6, 1);
    LocalDate endDate = LocalDate.of(2025, 6, 30);

    // ✅ 리포지터리 메서드에 정확한 타입 전달
    List<MissionsEntity> missionMonthList = missionsRepository.getMissionMonthList(
        startDate, endDate, userUuid);

    return missionMonthList.stream()
        .map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build())
        .collect(Collectors.toList());
  }


}
