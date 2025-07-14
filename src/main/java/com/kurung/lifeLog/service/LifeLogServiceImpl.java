package com.kurung.lifeLog.service;


import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.dto.MonthlyLifeLogDTO;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import com.kurung.lifeLog.repository.LifeLogRepository;
import com.kurung.lifeLog.repository.MonthlyLifeLogRepository;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LifeLogServiceImpl implements LifeLogService{

  private final LifeLogRepository lifeLogRepository;
  private final MonthlyLifeLogRepository monthlyLifeLogRepository;
  private final UserService userService;

  // 라이프 로그 단일 조회
  @Override
  public LifeLogDTO getLifeLogById(int id) {
    return LifeLogDTO.toLifeLogBuilder()
        .lifeLogEntity(lifeLogRepository.findById(id)
            .orElseThrow(() -> new CustomIllegalArgumentException(CustomHttpStatus.DIET_NOT_FOUND)))
        .build();
  }

  // 라이프 로그 한 달 조회
  @Override
  public List<LifeLogDTO> getLifeLogList(String userUuid, String date) {
    Date sqlDate = Date.valueOf(date);
    LocalDate localDate = sqlDate.toLocalDate();

    // 해당 월의 시작일과 종료일 계산
    LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
    LocalDate lastDayOfMonth = localDate.withDayOfMonth(localDate.lengthOfMonth());

    // LocalDateTime 범위 설정
    LocalDateTime startDateTime = firstDayOfMonth.atStartOfDay();
    LocalDateTime endDateTime = lastDayOfMonth.atTime(23, 59, 59);

    // 레포지토리 조회
    List<LifeLogEntity> lifeLogEntities =lifeLogRepository.findByUser_UserUuidAndCreatedAtBetween(
        userUuid, startDateTime, endDateTime);

    return lifeLogEntities.stream()
        .map(LifeLogDTO::new)
        .collect(Collectors.toList());
  }

  // 라이프 로그 생성
  @Override
  public void createLifelog(LifeLogDTO lifeLogDTO) {
    UserDTO userDTO = userService.getUserByUuid(lifeLogDTO.getUser().getUserUuid());

    try {
      LifeLogEntity logEntity = LifeLogEntity.builder()
          .emotion(lifeLogDTO.getEmotion())
          .emotionWrite(lifeLogDTO.getEmotionWrite())
          .bedTime(lifeLogDTO.getBedTime())
          .wakeupTime(lifeLogDTO.getWakeupTime())
          .activity(lifeLogDTO.getActivity())
          .memo(lifeLogDTO.getMemo())
          .llPdfPath(lifeLogDTO.getLlPdfPath())
          .user(UserEntity.builder()
              .userUuid(userDTO.getUserUuid())
              .userId(userDTO.getUserId())
              .userFaceLoginRef(userDTO.getUserFaceLoginRef())
              .userPwd(userDTO.getUserPwd())
              .userNick(userDTO.getUserNick())
              .userGender(userDTO.getUserGender())
              .userAge(userDTO.getUserAge())
              .userPath(userDTO.getUserPath())
              .adminYN(userDTO.isAdminYN())
              .isActive(userDTO.isActive())
              .build())
          .build();

      // 엔티티 저장
      lifeLogRepository.save(logEntity);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.LIFELOG_SAVE_ERROR);
    }

  }

  @Override
  @Transactional
  public void updateLifeLog(LifeLogDTO lifeLogDTO) {
    // 수정할 라이프 로그 조회
    LifeLogEntity lifeLogEntity = lifeLogRepository.getLifeLogById(lifeLogDTO.getLifelogId());

    if(lifeLogEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.LIFELOG_NOT_FOUND);
    }

    try{
      lifeLogEntity.updateLifeLog(lifeLogDTO);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.LIFELOG_UPDATE_ERROR);
    }
  }

  @Override
  public void deleteLifeLogById(int id) {
    // 삭제할 라이프 로그 조회
    LifeLogEntity lifeLogEntity = lifeLogRepository.getLifeLogById(id);

    if(lifeLogEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.LIFELOG_NOT_FOUND);
    }

    try {
      lifeLogRepository.delete(lifeLogEntity);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.LIFELOG_DELETE_ERROR);
    }
  }

  @Override
  public MonthlyLifeLogDTO getMonthlyLifeLog(String userUuid, String date) {
    // 월간 리포트 조회
    MonthlyLifeLogEntity monthlyLifeLogEntity = monthlyLifeLogRepository.findByMonthlyLifeLog_UserUuidAndMonth(userUuid, date);

    if(monthlyLifeLogEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.LIFELOG_NOT_FOUND);
    }

    try {

      YearMonth yearMonth = YearMonth.parse(date);
      LocalDate firstDayOfMonth = yearMonth.atDay(1);
      LocalDate lastDayOfMonth = yearMonth.atEndOfMonth();

      LocalDateTime startDateTime = firstDayOfMonth.atStartOfDay();
      LocalDateTime endDateTime = lastDayOfMonth.atTime(23, 59, 59);

      UserDTO userDTO = userService.getUserByUuid(userUuid);

      // 라이프 로그 엔티티 조회
      List<LifeLogEntity> lifeLogEntities = lifeLogRepository.findByUser_UserUuidAndCreatedAtBetween(
          userUuid, startDateTime, endDateTime);

      List<LifeLogDTO> lifeLogList = lifeLogEntities.stream().map(LifeLogDTO::new).toList();

      // 평균 수면 시간 계산
      List<Integer> sleepMinutesList = lifeLogEntities.stream().filter(
              lifeLogEntity -> lifeLogEntity.getBedTime() != null
                  && lifeLogEntity.getWakeupTime() != null)
          .map(lifeLogEntity -> {
            LocalDateTime bedTime = lifeLogEntity.getBedTime();
            LocalDateTime wakeupTime = lifeLogEntity.getWakeupTime();
            long minutes = Duration.between(bedTime, wakeupTime).toMinutes();
            return (int) Math.max(minutes, 0);
          }).toList();

      double avgSleepMinute = sleepMinutesList.isEmpty() ? 0.0
          : sleepMinutesList.stream().mapToInt(Integer::intValue).average().orElse(0)/60.0;

      double avgSleepMinutes = Math.round(avgSleepMinute * 10) / 10.0;





      // 라이프 로그 갯수 카운트
      int countLifeLog = lifeLogEntities.size();

      // 감정별 갯수 카운트
      Map<String, Long> emotionCount = lifeLogEntities.stream()
          .collect(Collectors.groupingBy(LifeLogEntity::getEmotion, Collectors.counting()));

      return MonthlyLifeLogDTO.builder()
          .monthlyLifeLogId(monthlyLifeLogEntity.getMonthlyLifeLogId())
          .month(monthlyLifeLogEntity.getMonth())
          .monthlySummary(monthlyLifeLogEntity.getMonthlySummary())
          .lifeLogList(lifeLogList)
          .user(userDTO)
          .avgSleepTime(avgSleepMinutes)
          .countLifeLog(countLifeLog)
          .countHappy(emotionCount.getOrDefault("행복함", 0L).intValue())
          .countCalm(emotionCount.getOrDefault("평온함", 0L).intValue())
          .countHappy(emotionCount.getOrDefault("행복함", 0L).intValue())
          .countCalm(emotionCount.getOrDefault("평온함", 0L).intValue())
          .countHappy(emotionCount.getOrDefault("행복함", 0L).intValue())
          .countCalm(emotionCount.getOrDefault("평온함", 0L).intValue())
          .countHappy(emotionCount.getOrDefault("행복함", 0L).intValue())
          .countCalm(emotionCount.getOrDefault("평온함", 0L).intValue())
          .build();
    }catch (Exception e){
      throw new CustomRunTimeException(CustomHttpStatus.MONTHLYLIFELOG_NOT_FOUND);
    }
  }
}
