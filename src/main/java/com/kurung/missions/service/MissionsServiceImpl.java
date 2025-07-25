package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.security.service.SessionService;
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
  private final SessionService sessionService;

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
  public List<MissionsDTO> getMissionMonthList(LocalDate currentDate, HealthType displayType) {
    UserDTO userDTO = sessionService.getUserFromToken();
    // 현재 월의 시작과 끝 구하기
    LocalDate startDate = LocalDate.from(currentDate.withDayOfMonth(1).atStartOfDay());
    LocalDate endDate = LocalDate.from(
        currentDate.withDayOfMonth(currentDate.lengthOfMonth()).atStartOfDay());

    List<MissionsEntity> missionMonthList = missionsRepository.getMissionMonthList(
        startDate, endDate, userDTO.getUserUuid(), displayType
    );

    return missionMonthList.stream()
        .map(missionsEntity -> MissionsDTO.toMissionBuilder().missionEntity(missionsEntity).build())
        .collect(Collectors.toList());
  }


}
