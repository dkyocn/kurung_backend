package com.kurung.exercise.service;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import com.kurung.common.exception.CustomRunTimeException;
import com.kurung.common.security.service.SessionService;
import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.dto.SummaryDTO.ExerciseLogDTO;
import com.kurung.exercise.dto.SummaryDTO.MonthlySummaryDTO;
import com.kurung.exercise.entity.ExerciseEntity;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.exercise.entity.ObjectiveEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.exercise.repository.ExerciseLogRepository;
import com.kurung.exercise.repository.ExerciseRepository;
import com.kurung.exercise.repository.ObjectiveRepository;
import com.kurung.exercise.repository.RoutinesRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.service.UserService;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.WeekFields;
import java.util.Optional;
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
  private final SessionService sessionService;


  // ExerciseLog create -------------------------------------------------------
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

  // ExerciseLog Updated ---------------------------------
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

  // ExerciseLog Delete ---------------------------------------
  @Override
  @Transactional
  public void deleteExerciseLog(int id) {
    Optional<ExerciseLogEntity> optionalEntity = exerciseLogRepository.findById(id);

    if (!optionalEntity.isPresent()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.EXERCISELOG_NOT_FOUND);
    }

    ExerciseLogEntity entity = optionalEntity.get();
    exerciseLogRepository.delete(entity);
  }

  // ExerciseLog Selected ---------------------------------------
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
        .totalDuration(totalDuration)
        .totalKcal(totalKcal)
        .routineCount(routineCount)
        .goalAchievementRate(80)
        .exerciseList(Collections.emptyList()) // 또는 나중에 실제 아이템 리스트
        .build();
  }

  // SummaryMonthly ---------------------------------
  @Override
  public SummaryDTO.MonthlySummaryDTO getMonthlySummary(String userUuid, YearMonth month) {
    LocalDate start = month.atDay(1);
    LocalDate end = month.atEndOfMonth();

    List<ExerciseLogEntity> logs = exerciseLogRepository.findSummarysByUserUuid(
        userUuid, start.atStartOfDay(), end.atTime(23, 59, 59)
    );

    int totalDuration = logs.stream().mapToInt(ExerciseLogEntity::getDuration).sum();
    int totalKcal = logs.stream().mapToInt(ExerciseLogEntity::getCalories).sum();
    int routineCount = logs.size(); // 운동 기록의 총 갯수로 변경

    // 목표값 조회 및 달성률 계산
    ObjectiveEntity objective = objectiveRepository.getObjectiveByMonth(
        start.atStartOfDay(), end.atTime(23, 59, 59), userUuid);

    int targetRoutineCount = 0;
    int targetDuration = 0;
    if (objective != null) {
      targetRoutineCount = objective.getObjectiveCount();
      targetDuration = objective.getObjectiveDuration();
    }

    double routineRate = targetRoutineCount > 0
        ? Math.round((double) routineCount / targetRoutineCount * 1000) / 10.0
        : 0.0;
    double durationRate = targetDuration > 0
        ? Math.round((double) totalDuration / targetDuration * 1000) / 10.0
        : 0.0;
    double goalAchievementRate = Math.round((routineRate + durationRate) / 2 * 10) / 10.0;


    // 주차별 정보
    int[] weeklyRoutineCounts = new int[4];
    int[] weeklyDurations = new int[4];
    int[] weeklyKcals = new int[4];

    for (ExerciseLogEntity log : logs) {
      int week = log.getExerciseDate().get(WeekFields.ISO.weekOfMonth()) - 1;
      if (week >= 0 && week < 4) {
        weeklyRoutineCounts[week]++;
        weeklyDurations[week] += log.getDuration();
        weeklyKcals[week] += log.getCalories();
      }
    }

    return SummaryDTO.MonthlySummaryDTO.builder()
        .totalDuration(totalDuration)
        .totalKcal(totalKcal)
        .routineCount(routineCount)
        .goalAchievementRate(goalAchievementRate)
        .weeklyRoutineCounts(weeklyRoutineCounts)
        .weeklyDurations(weeklyDurations)
        .weeklyKcals(weeklyKcals)
        .build();
  }

  // SummaryDailyList --------------------------------
  @Override
  public SummaryDTO getSummaryDailyList(String userUuid, LocalDate date) {
    LocalDateTime start = date.atStartOfDay();           // 2025-06-02 00:00:00
    LocalDateTime end = date.plusDays(1).atStartOfDay(); // 2025-06-03 00:00:00

    List<ExerciseLogEntity> logs = exerciseLogRepository.findSummarysByUserUuid(userUuid, start,
        end);

    for (ExerciseLogEntity log : logs) {
      System.out.println("exerciseLogsId: " + log.getExerciseLogsId() + ", date: " + log.getExerciseDate());
    }

    int totalDuration = logs.stream().mapToInt(ExerciseLogEntity::getDuration).sum();
    int totalKcal = logs.stream().mapToInt(ExerciseLogEntity::getCalories).sum();
    int routineCount = logs.size();

    return SummaryDTO.builder()
        .date(date)
        .totalDuration(totalDuration)
        .totalKcal(totalKcal)
        .routineCount(routineCount)
        .exerciseList(logs.stream()
            .map(SummaryDTO.ExerciseLogDTO::new)
            .collect(Collectors.toList()))
        .build();
  }

  // Objective ----------------------------------------
  @Override
  @Transactional
  public void updateObjectiveaction(int objectiveId) {
    ObjectiveEntity entity = objectiveRepository.findByObjectiveId(objectiveId);
    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.OBJECTIVE_NOT_FOUND);
    }
    entity.updateIsActive();
  }


  // Objective Monthly -------------------------------
  @Override
  public ObjectiveDTO getObjectiveByMonth(LocalDateTime date, String userUuid) {

    UserDTO userByUuid = userService.getUserByUuid(userUuid);

    // 1. 월 시작과 종료 날짜 계산
    LocalDateTime startOfMonth = date.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
    LocalDateTime endOfMonth = date.withDayOfMonth(date.toLocalDate().lengthOfMonth())
        .withHour(23).withMinute(59).withSecond(59);

    // 2. 해당 월에 해당하는 목표 엔티티 조회
    ObjectiveEntity objectiveEntity = objectiveRepository.getObjectiveByMonth(startOfMonth,
        endOfMonth, userUuid);

    // 3. 결과가 없으면 null 반환
    if (objectiveEntity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.OBJECTIVE_NOT_FOUND);
    }

    // 5. ObjectiveDTO로 변환해서 반환
    return ObjectiveDTO.toObjectiveBuilder()
        .objectiveEntity(objectiveEntity)
        .userDTO(userByUuid)
        .build();
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

  @Override
  public ObjectiveDTO getObjectiveById(int objectiveId) {
    ObjectiveEntity entity = objectiveRepository.findByObjectiveId(objectiveId);
    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.OBJECTIVE_NOT_FOUND);
    }
    return ObjectiveDTO.toObjectiveBuilder().objectiveEntity(entity).build();
  }


  // Routines ----------------------------------------------------------------------
  @Override
  public RoutinesDTO getRoutinesById(int id) {
    RoutinesEntity entity = routinesRepository.getRoutinesById(id);
    if (entity == null) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.ROUTINE_NOT_FOUND);
    }
    return RoutinesDTO.builder()
        .routinesId(entity.getRoutinesId())
        .user(entity.getUser() != null ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build() : null)
        .title(entity.getTitle())
        .routineLevel(entity.getRoutineLevel())
        .place(entity.getPlace())
        .videoUrl(entity.getVideoUrl())
        .savedDate(entity.getSavedDate())
        .build();
  }

  @Override
  public List<RoutinesDTO> getRoutinesByUserAndDate(String userUuid, LocalDate date) {
    LocalDateTime start = date.atStartOfDay();
    LocalDateTime end = date.plusDays(1).atStartOfDay();

    List<RoutinesEntity> list = routinesRepository.findRoutinesByUserAndDate(userUuid, start, end);

    return list.stream()
        .map(RoutinesDTO::new)
        .collect(Collectors.toList());
  }

  // RoutinesCreate ---------------------------------------------------------------
  @Transactional
  @Override
  public void createRoutine(RoutinesDTO routinesDTO) {
    // 1. userUuid로 UserDTO 조회
    UserDTO userByUuid = userService.getUserByUuid(routinesDTO.getUser().getUserUuid());

    // 2. 빌더로 Entity 생성 후 저장
    routinesRepository.save(
        RoutinesEntity.createRoutinesBuilder()
            .routinesDTO(routinesDTO)
            .userDTO(userByUuid)
            .build()
    );
  }

  // RoutinesDelete -------------------------------------------------------------------
  @Transactional
  @Override
  public void deleteRoutine(int routinesId) {
    Optional<RoutinesEntity> optionalEntity = routinesRepository.findById(routinesId);

    if (!optionalEntity.isPresent()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.ROUTINE_NOT_FOUND);
    }

    RoutinesEntity entity = optionalEntity.get();
    routinesRepository.delete(entity);
  }

  // Exercise ------------------------------------------------------------
  @Override
  public ExerciseDTO getExerciseById(int id) {
    ExerciseEntity entity = exerciseRepository.getExerciseById(id); // findByExerciseId가 null 반환하는 메서드여야 함
    if (entity == null) return null;
    return ExerciseDTO.builder()
        .exerciseId(entity.getExerciseId())
        .exerciseName(entity.getExerciseName())
        .exerciseCategory(entity.getExerciseCategory())
        .tool(entity.getTool())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  @Override
  public List<ExerciseDTO> getAllExercises() {
    return exerciseRepository.findAll()
        .stream()
        .map(ExerciseDTO::new) // entity → dto 변환
        .collect(Collectors.toList());
  }


  // ExerciseMonthlyTime -------------------------------------------------
  @Override
  public List<SummaryDTO> getMonthlyExerciseTime(LocalDateTime timeMonth) {
    UserDTO userDTO = sessionService.getUserFromToken();

    List<ExerciseLogEntity> exerciseMonthList = exerciseLogRepository.findSummarysByUserUuid(
        userDTO.getUserUuid(), timeMonth.toLocalDate().withDayOfMonth(1).atStartOfDay(),
        timeMonth.toLocalDate().withDayOfMonth(timeMonth.toLocalDate().lengthOfMonth())
            .atStartOfDay()
    );

    return exerciseMonthList.stream()
        .map(entity -> SummaryDTO.builder()
            .date(entity.getExerciseDate().toLocalDate())
            .totalDuration(entity.getDuration())
            .build())
        .collect(Collectors.toList());
  }


}
