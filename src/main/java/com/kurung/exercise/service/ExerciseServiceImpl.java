package com.kurung.exercise.service;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseLogEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.exercise.repository.ExerciseLogRepository;
import com.kurung.exercise.repository.ExerciseLogRepositorySupport;
import com.kurung.exercise.repository.ExerciseRepository;
import com.kurung.exercise.repository.ObjectiveRepository;
import com.kurung.exercise.repository.RoutinesRepository;
import com.kurung.exercise.repository.RoutinesRepositorySupportImpl;
import com.kurung.user.dto.UserDTO;
import java.time.YearMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

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
    public List<SummaryDTO.ExerciseLogDTO> getExerciseLogsByUser(String uuid) {
        return exerciseLogRepository.findAll().stream()
                .filter(log -> log.getUser().equals(uuid))
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
    public SummaryDTO getSummaryByUser(String uuid) {
        List<ExerciseLogEntity> logs = exerciseLogRepository.getLogsByUserUuid(uuid);

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
    public List<MonthlyExerciseDTO> getMonthlyExerciseTime(String uuid, int year, int month) {
        // 1일과 말일 날짜 구하기
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // 유저의 운동 로그 조회 (Repository는 java.sql.Date로 받음)
        List<ExerciseLogEntity> logs = exerciseLogRepository.getMonthlyExerciseTime(
            uuid,
            java.sql.Date.valueOf(startDate),
            java.sql.Date.valueOf(endDate)
        );

        // 총 운동 시간 계산
        int totalDuration = logs.stream()
            .mapToInt(ExerciseLogEntity::getDuration)
            .sum();

        // 년-월 형식 문자열 만들기 (예: 2025-06)
        String yearMonthStr = year + "-" + String.format("%02d", month);
        LocalDate parsedDate = LocalDate.parse(yearMonthStr + "-01"); // 1일로 변환

        // DTO 생성 및 반환
        return List.of(MonthlyExerciseDTO.builder()
            .date(String.valueOf(LocalDate.parse(parsedDate.toString())))  // 또는 .date(yearMonthStr)
            .totalDuration(totalDuration)
            .build());
    }


}
