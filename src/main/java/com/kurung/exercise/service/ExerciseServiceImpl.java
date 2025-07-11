package com.kurung.exercise.service;

import static com.kurung.exercise.entity.QExerciseLogEntity.exerciseLogEntity;

import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.exercise.repository.ExerciseLogRepository;
import com.kurung.exercise.repository.ExerciseRepository;
import com.kurung.exercise.repository.ObjectiveRepository;
import com.kurung.exercise.repository.RoutinesRepository;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.repository.UserRepository;
import com.kurung.user.service.UserService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

  private final UserService userService;
  private final ExerciseRepository exerciseRepository;
  private final ExerciseLogRepository exerciseLogRepository;
  private final ObjectiveRepository objectiveRepository;
  private final RoutinesRepository routinesRepository;

  @Override
  public SummaryDTO.ExerciseLogDTO createExerciseLog(SummaryDTO.ExerciseLogDTO dto) {
    ExerciseLogEntity saved = exerciseLogRepository.save(ExerciseLogEntity.builder().build());
    return SummaryDTO.ExerciseLogDTO.toExerciseLogBuilder()
        .entity(saved)
        .build();
  }

  @Override
  public List<SummaryDTO.ExerciseLogDTO> getExerciseLogsByUser(String userUuid) {
    return exerciseLogRepository.findAll().stream()
        .filter(log -> log.getUser().equals(userUuid))
        .map(entity -> SummaryDTO.ExerciseLogDTO.toExerciseLogBuilder()
            .entity(entity)
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public SummaryDTO.ExerciseLogDTO getExerciseLogById(int id) {
    return exerciseLogRepository.findById(id)
        .map(entity -> SummaryDTO.ExerciseLogDTO.toExerciseLogBuilder()
            .entity(entity)
            .build())
        .orElse(null);
  }

  @Override
  public void deleteExerciseLog(int id) {
    exerciseLogRepository.deleteById(id);
  }

  // SUMMARY LINE -----------------------------------------------------------
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
  public ObjectiveDTO getObjectiveById(int id) {
    return objectiveRepository.findById(id)
        .map(entity -> ObjectiveDTO.builder()
            .objectiveId(entity.getObjectiveId())
            .user(UserDTO.toUserBuilder()
                .userEntity(entity.getUser())
                .build())
            .objectiveTitle(entity.getObjectiveTitle())
            .objectiveCount(entity.getObjectiveCount())
            .objectiveDuration(entity.getObjectiveDuration())
            .objectiveWeight(entity.getObjectiveWeight())
            .startDate(entity.getStartDate())
            .endDate(entity.getEndDate())
            .memo(entity.getMemo())
            .isActive(entity.getIsActive())
            .createdAt(entity.getCreatedAt())
            .lastUpdatedAt(entity.getLastUpdatedAt())
            .build()).orElseThrow();
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
