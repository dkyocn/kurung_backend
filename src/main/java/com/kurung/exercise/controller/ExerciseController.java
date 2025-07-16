package com.kurung.exercise.controller;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlySummaryDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/exercise")
@Tag(name = "Exersice TEST", description = "운동 API 테스트코드입니다.")

public class ExerciseController {

  private final ExerciseService exerciseService;

  // ExerciseLog --------------------------------------------
  @PostMapping("/log/create")
  @Operation(summary = "운동 기록 저장", description = "운동 기록을 저장하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "531", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "exerciseLogDTO", description = "운동 기록 저장 데이터")
  public ResponseEntity<HttpStatus> createExerciseLog(
      @RequestBody SummaryDTO.ExerciseLogDTO exerciseLogDTO) {
    exerciseService.createExerciseLog(exerciseLogDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/log/update")
  @Operation(summary = "운동기록 수정", description = "운동기록을 수정하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "534", description = "수정 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "회원 아이디", example = "2025061401")
  public ResponseEntity<SummaryDTO.ExerciseLogDTO> updateExerciseLog(
      @RequestBody SummaryDTO.ExerciseLogDTO exerciseLogDTO) {
    return new ResponseEntity<>(exerciseService.updateExerciseLog(exerciseLogDTO), HttpStatus.OK);
  }

  @DeleteMapping("/log/delete/{id}")
  @Operation(summary = "운동기록 삭제", description = "운동기록을 삭제하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "삭제 성공"),
      @ApiResponse(responseCode = "535", description = "삭제 실패")
  })
  @Parameter(name = "id", description = "운동기록 ID", example = "3")
  public ResponseEntity<SummaryDTO.ExerciseLogDTO> deleteExerciseLog(@PathVariable int id) {
    exerciseService.deleteExerciseLog(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  // 운동 기록 단일 조회 (ID 기반)
  @GetMapping("/log/select/{id}")
  @Operation(summary = "운동기록 단일 조회", description = "운동요약 entity, dto 연동 확인")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "회원 아이디", example = "2025061401")
  public ResponseEntity<SummaryDTO.ExerciseLogDTO> getExerciseLogById(@PathVariable int id) {
    return new ResponseEntity<>(exerciseService.getExerciseLogById(id), HttpStatus.OK);
  }

  // 아이디 받은걸로 조회 후 Entity로 해서 isAction 변경

  // SUMMARY ----------------------------------
  /* @GetMapping("/summary/{userUuid}")
  @Operation(summary = "운동요약 DB 연동 확인", description = "운동요약 entity, dto 연동 확인")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "userUuid", description = "회원 아이디", example = "2025061401")
  public ResponseEntity<SummaryDTO> getSummaryByUser(@PathVariable String userUuid) {
    return new ResponseEntity<>(exerciseService.getSummaryByUser(userUuid), HttpStatus.OK);
  } */

  // SummaryDailyList ----------------------------
  @GetMapping("/summary/daily/{userUuid}")
  @Operation(summary = "운동일일요약 연동 확인", description = "운동일일 요약 데이터 확인.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "상태 변경 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "해당 목표 없음", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<SummaryDTO> getSummaryDailyList(
      @PathVariable String userUuid,
      @RequestParam LocalDate date
  ) {
    return new ResponseEntity<>(exerciseService.getSummaryDailyList(userUuid, date), HttpStatus.OK);
  }

  // SummaryMonthly ---------------------------
  @GetMapping("/summary/monthly/{userUuid}")
  @Operation(summary = "운동월간요약 연동 확인", description = "운동월간 요약 데이터 확인")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "상태 변경 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "해당 월 데이터 없음", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<MonthlySummaryDTO> getMonthlySummary(
      @PathVariable String userUuid,
      @RequestParam YearMonth month
  ) {
    return new ResponseEntity<>(exerciseService.getMonthlySummary(userUuid, month), HttpStatus.OK);
  }

  // Objective -----------------------------
  @PostMapping("/objective/activation/{objectiveId}")
  @Operation(summary = "운동목표 활성화/비활성화", description = "목표 ID와 활성화 여부를 받아 상태를 변경합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "상태 변경 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "해당 목표 없음", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> updateObjectiveAction(@PathVariable int objectiveId) {
    exerciseService.updateObjectiveaction(objectiveId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // objective selecte --------------------
  @GetMapping("/objective/monthly")
  @Operation(summary = "운동목표 월별 단일 조회", description = "현재 년월에 해당하는 운동 목표 1개만 조회합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "해당 월에 목표 없음", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<ObjectiveDTO> getMonthlyObjective(
      @RequestParam String userUuid,
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
  ) {
    return new ResponseEntity<>(exerciseService.getObjectiveByMonth(date, userUuid), HttpStatus.OK);
  }


  @PostMapping("/objective/created")
  @Operation(summary = "목표 생성", description = "목표 정보를 저장합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "532", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "userUuid", description = "회원 아이디", example = "2025061401")
  public ResponseEntity<HttpStatus> createObjective(@RequestBody ObjectiveDTO objectiveDTO) {
    exerciseService.createObjective(objectiveDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/objective/updated")
  @Operation(summary = "목표 수정", description = "기존 목표 정보를 수정합니다.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "571", description = "수정 실패", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> updateObjective(@RequestBody ObjectiveDTO objectiveDTO) {
    exerciseService.updateObjective(objectiveDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // Routines ------------------------------
  @GetMapping("/routines/{id}")
  @Operation(summary = "루틴추천 DB 연동 확인", description = "루틴추천 entity, dto 연동 확인")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "469", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "회원 아이디", example = "1")
  public ResponseEntity<RoutinesDTO> getRoutinesById(@PathVariable int id) {
    return new ResponseEntity<>(exerciseService.getRoutinesById(id), HttpStatus.OK);
  }

  // Exercise ---------------------------------
  @GetMapping("/exercise/{id}")
  @Operation(summary = "운동종목 및 url DB 연동 확인", description = "운동종목 및 url entity, dto 연동 확인")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "469", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "회원 아이디", example = "1")
  public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable int id) {
    return new ResponseEntity<>(exerciseService.getExerciseById(id), HttpStatus.OK);
  }

  // ExerciseMonthlyTime(건강리포트) ---------------------------------
  @GetMapping("/exerciseMonthlyTime/{userUuid}")
  @Operation(summary = "월간 총 운동 시간 조회", description = "사용자의 월간 총 운동 시간을 조회합니다")
  @ApiResponses({
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "468", description = "조회 실패", content = @Content(mediaType = "application/json"))
  })
  @Parameters({
      @Parameter(name = "timeMonth", description = "오늘 날짜", example = "2025-05-19T00:00:00"),
      @Parameter(name = "userUuid", description = "회원 UUID", example = "2025061401")
  })
  public ResponseEntity<List<SummaryDTO>> getMonthlyExerciseTime(
      @RequestParam LocalDateTime timeMonth,
      @PathVariable String userUuid
  ) {
    List<SummaryDTO> result = exerciseService.getMonthlyExerciseTime(timeMonth, userUuid);
    return ResponseEntity.ok(result);
  }


}
