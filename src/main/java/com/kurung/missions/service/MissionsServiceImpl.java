package com.kurung.missions.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.enumeration.HealthType;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.security.service.SessionService;
import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import com.kurung.lifeLog.repository.MonthlyHabitMissionsRepository;
import com.kurung.missions.dto.MissionsBadgeDTO;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.entity.MissionsBadgeEntity;
import com.kurung.missions.entity.MissionsEntity;
import com.kurung.missions.repository.MissionsBadgeRepository;
import com.kurung.missions.repository.MissionsBadgeRepositorySupport;
import com.kurung.missions.repository.MissionsRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MissionsServiceImpl implements MissionsService {

  private final MissionsRepository missionsRepository;
  private final UserService userService;
  private final SessionService sessionService;
  private final MonthlyHabitMissionsRepository monthlyHabitMissionsRepository;
  private final MissionsBadgeRepository missionsBadgeRepository;
  private final UserRepository userRepository;

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
  public List<MissionsDTO> getTodayMissions() {

    UserDTO userDTO = sessionService.getUserFromToken();

    LocalDate today = LocalDate.now();
    LocalDateTime startOfDay = today.atStartOfDay();         // 00:00:00
    LocalDate localDate = startOfDay.toLocalDate();

    List<MissionsEntity> missionList = missionsRepository.getTodayMissions(userDTO.getUserUuid(), localDate);

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

  @Override
  @Transactional
  public void updateMissions(MissionsDTO missionsDTO) {

    MissionsEntity entity = missionsRepository.getById(missionsDTO.getMissionId());

    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.MISSIONS_NOT_FOUND);

    }

    try{
      entity.updateMissions(missionsDTO);
    } catch (Exception e) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.MISSIONS_NOT_FOUND);
    }

  }


  // 습관 미션 1개 랜덤 저장
  @Transactional
  @Override
  public void createMission() {
    LocalDate now = LocalDate.now();
    LocalDate firstDayOfLastMonth = now.minusMonths(1).withDayOfMonth(1);
    LocalDate lastDayOfLastMonth = firstDayOfLastMonth.withDayOfMonth(
        firstDayOfLastMonth.lengthOfMonth());

    LocalDateTime startDate = firstDayOfLastMonth.atStartOfDay();
    LocalDateTime endDate = lastDayOfLastMonth.atTime(LocalTime.MAX);

    // 모든 유저 가져오기
    List<UserEntity> users = userRepository.findAll();
    log.info("사용자 정보 : {}", users.size());

    for (UserEntity user : users) {
      // 지난 달 추천 습관 목록 가져오기
      List<MonthlyHabitMissionsEntity> monthlyHabitMissions = monthlyHabitMissionsRepository.findByUser_UserUuidAndMonthlyHabitDate(
          user.getUserUuid(), startDate, endDate
      );

      if (monthlyHabitMissions.isEmpty()) {
        continue;
      }

      // 중복 저장 방지 : 해당 날짜에 이미 저장된 미션이 있는지 확인
      List<MissionsEntity> alreadyExists = missionsRepository.getMissionList(user.getUserUuid(),
          now);
      if (!alreadyExists.isEmpty()) {
//        continue;
      }

      // 랜덤 1개 선택
      MonthlyHabitMissionsEntity selectedHabit = monthlyHabitMissions.get(
          new Random().nextInt(monthlyHabitMissions.size()));

      // 미션 저장
      MissionsEntity mission = MissionsEntity.builder()
          .user(user)
          .startedDate(LocalDateTime.now())
          .isComplete(false)
          .displayType(HealthType.DAILY)
          .toggleOption(false)
          .habitRec(selectedHabit.getHabitRecId())
          .build();

      log.info("미션 저장: user={}, habitRecId={}", user.getUserUuid(),
          selectedHabit.getHabitRecId().getHabitRecId());
      missionsRepository.save(mission);
    }
  }

    @Override
    @Transactional
    public MissionsBadgeDTO checkTodayAllCompleted() {
      LocalDate today = LocalDate.now();

      UserDTO userDTO = sessionService.getUserFromToken();
      MissionsBadgeEntity missionsBadgeEntity = missionsBadgeRepository.todayMissionBadge(
          userDTO.getUserUuid(), today);

      List<MissionsEntity> missionList = missionsRepository.getMissionList(userDTO.getUserUuid(),
          today);

      if (missionList.stream().allMatch(MissionsEntity::isComplete)) {
        missionsBadgeEntity.updateBadge();
      }



      // ✅ 모든 미션 완료 여부 확인
      return MissionsBadgeDTO.toMissionsBadgeCuilder().missionsBadgeEntity(missionsBadgeEntity).build();
    }

}
