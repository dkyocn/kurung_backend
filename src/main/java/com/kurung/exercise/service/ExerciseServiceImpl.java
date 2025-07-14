package com.kurung.exercise.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.dto.SummaryDTO.ExerciseLogDTO;
import com.kurung.exercise.entity.ExerciseEntity;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.exercise.repository.ExerciseLogRepository;
import com.kurung.exercise.repository.ExerciseRepository;
import com.kurung.exercise.repository.ObjectiveRepository;
import com.kurung.exercise.repository.RoutinesRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

  private final UserService userService;
  private final ExerciseRepository exerciseRepository;
  private final ExerciseLogRepository exerciseLogRepository;
  private final ObjectiveRepository objectiveRepository;
  private final RoutinesRepository routinesRepository;


  // Exercise create -------------------------------------------------------
  @Transactional
  @Override
  public void createExerciseLog(ExerciseLogDTO exerciseLogDTO) {
    // 1. UserEntity 조회 (UserDTO에 userUuid가 반드시 들어와야 함)
    UserDTO userByUuid = userService.getUserByUuid(exerciseLogDTO.getUser().getUserUuid());

    ExerciseEntity exerciseById = exerciseRepository.getExerciseById(
        exerciseLogDTO.getExercise().getExerciseId());
    if (exerciseById != null) {
      // 2. DB에 저장
      exerciseLogRepository.save(ExerciseLogEntity.createExerciseLogBuilder()
          .exerciseLogDTO(exerciseLogDTO)
          .userDTO(userByUuid)
          .exerciseEntity(exerciseById)
          .build());
    } else {
      throw new CustomIllegalArgumentException(CustomHttpStatus.EXERCISE_NOT_FOUND);
    }
  }

  // Exercise Updated ---------------------------------
  @Override
  @Transactional
  public SummaryDTO.ExerciseLogDTO updateExerciseLog(SummaryDTO.ExerciseLogDTO exerciseLogDTO) {
    // 1. 기존 기록 조회
    ExerciseLogEntity entity = exerciseLogRepository.getReferenceById(
        exerciseLogDTO.getExerciseLogsId());
    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.EXERCISELOG_NOT_FOUND);
    }
    try {
      // 2. 엔티티에 값 반영
      entity.updateFromDTO(exerciseLogDTO);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.EXERCISE_UPDATE_ERROR);
    }
    return SummaryDTO.ExerciseLogDTO.toExerciseLogBuilder()
        .entity(entity)
        .build();
  }

  // Exercise Delete ---------------------------------------
  @Override
  @Transactional
  public void deleteExerciseLog(int id) {
    ExerciseLogEntity entity = exerciseLogRepository.findById(id).orElse(null);

    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.EXERCISELOG_NOT_FOUND);
    }

    exerciseLogRepository.delete(entity);
  }

  // Exercise Selected ---------------------------------------
  @Override
  public ExerciseLogDTO getExerciseLogById(int id) {
    ExerciseLogEntity entity = exerciseLogRepository.getExerciseLogById(id);

    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.EXERCISELOG_NOT_FOUND);
    }

    return ExerciseLogDTO.toExerciseLogBuilder()
        .entity(entity)
        .build();
  }

  // SUMMARY  -----------------------------------------------------------
  @Override
  public SummaryDTO getSummaryByUser(String userUuid) {
    List<ExerciseLogEntity> logs = exerciseLogRepository.getLogsByUserUuid(userUuid);

    int totalDuration = logs.stream().mapToInt(ExerciseLogEntity::getDuration).sum();
    int totalKcal = logs.stream().mapToInt(ExerciseLogEntity::getCalories).sum();
    int routineCount = logs.size();

    return SummaryDTO.builder()
        .date(LocalDate.now().toString())
        .totalDuration(totalDuration)
        .totalKcal(totalKcal)
        .routineCount(routineCount)
        .goalAchievementRate(80)
        .exerciseList(Collections.emptyList()) // 또는 나중에 실제 아이템 리스트
        .build();
  }

  // Objective ----------------------------------------
  @Override
  public List<ObjectiveDTO> getObjectiveMonthList(LocalDateTime date, String userUuid) {
    LocalDateTime startOfMonth = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
    LocalDateTime endOfMonth = date.withDayOfMonth(date.toLocalDate().lengthOfMonth())
        .withHour(23).withMinute(59).withSecond(59);

    List<ObjectiveEntity> objectiveEntities = objectiveRepository
        .getObjectiveMonthList(startOfMonth, endOfMonth, userUuid);

    return objectiveEntities.stream()
        .map(entity -> ObjectiveDTO.toObjectiveBuilder().entity(entity).build())
        .collect(Collectors.toList());
  }

  // Objective Updated -------------------------------
  @Override
  @Transactional
  public void updateObjective(ObjectiveDTO objectiveDTO) {
    // 1. 수정할 목표 조회
    ObjectiveEntity objectiveEntity = objectiveRepository.getReferenceById(
        objectiveDTO.getObjectiveId());

    if (objectiveEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.OBJECTIVE_NOT_FOUND);
    }

    try {
      // 2. 엔티티에 값 반영
      objectiveEntity.updateObjective(objectiveDTO);
    } catch (Exception e) {
      throw new CustomRunTimeException(CustomHttpStatus.OBJECT_UPDATE_ERROR);
    }
  }


  // Objective created -----------------------
  @Transactional
  @Override
  public void createObjective(ObjectiveDTO objectiveDTO) {
    // 1. UserDTO 조회 (userUuid 필요)
    UserDTO userByUuid = userService.getUserByUuid(objectiveDTO.getUser().getUserUuid());

    // 2. ObjectiveEntity 빌더 패턴 변환 및 저장
    objectiveRepository.save(
        ObjectiveEntity.createObjectiveBuilder()
            .objectiveDTO(objectiveDTO)
            .userDTO(userByUuid)
            .build()
    );
  }


  // Routines ----------------------------------------------------------------------
  @Override
  public RoutinesDTO getRoutinesById(int id) {
    return routinesRepository.findById(id)
        .map(entity -> RoutinesDTO.builder()
            .routinesId(entity.getRoutinesId())
            .user(UserDTO.toUserBuilder()
                .userEntity(entity.getUser())
                .build())
            .title(entity.getTitle())
            .routineLevel(entity.getRoutineLevel())
            .place(entity.getPlace())
            .videoUrl(entity.getVideoUrl())
            .savedDate(entity.getSavedDate())
            .build()).orElseThrow();
  }

  // Exercise ------------------------------------------------------------
  @Override
  public ExerciseDTO getExerciseById(int id) {
    return exerciseRepository.findById(id)
        .map(entity -> ExerciseDTO.builder()
            .exerciseId(entity.getExerciseId())
            .exerciseName(entity.getExerciseName())
            .exerciseCategory(entity.getExerciseCategory())
            .tool(entity.getTool())
            .createdAt(entity.getCreatedAt())
            .build()).orElseThrow();
  }

  // ExerciseMonthlyTime -------------------------------------------------
  @Override
  public List<MonthlyExerciseDTO> getMonthlyExerciseTime(LocalDateTime timeMonth, String userUuid) {
    //  user는 무조건 조회해서 가져와야함

    UserDTO userByUuid = userService.getUserByUuid(userUuid);

    // [1] 반드시 userUuid 포함 3개 파라미터 전달!
    List<ExerciseLogEntity> exerciseMonthList = exerciseLogRepository.getMonthlyExerciseTime(
        userUuid, timeMonth.toLocalDate().withDayOfMonth(1).atStartOfDay(),
        timeMonth.toLocalDate().withDayOfMonth(timeMonth.toLocalDate().lengthOfMonth())
            .atStartOfDay()
    );

    // [2] 반드시 MonthlyExerciseDTO, ExerciseLogEntity로 변환!
    return exerciseMonthList.stream()
        .map(entity -> MonthlyExerciseDTO.builder()
            .user(userByUuid)
            .date(entity.getCreatedAt())
            .totalDuration(entity.getDuration())
            .build())
        .collect(Collectors.toList());
  }


}
