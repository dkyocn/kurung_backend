package com.kurung.lifeLog.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.lifeLog.repository.LifeLogRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import com.kurung.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifeLogServiceImpl implements LifeLogService{

  private final LifeLogRepository lifeLogRepository;
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
}
