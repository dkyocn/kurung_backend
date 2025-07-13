package com.kurung.exercise.controller;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.MonthlyExerciseDTO;
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
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/exercise")
@Tag(name = "Exersice TEST", description = "운동 API 테스트코드입니다.")

public class ExerciseController {

    private final ExerciseService exerciseService;

//    // 1. 운동 기록 생성
//    @PostMapping
//    public ResponseEntity<ExerciseLogDTO> createExerciseLog(@RequestBody ExerciseLogDTO dto) {
//        ExerciseLogDTO savedLog = exerciseLogService.createExerciseLog(dto);
//        return ResponseEntity.ok(savedLog);
//    }

    @PostMapping("")
    @Operation(summary = "운동 기록 저장", description = "운동 기록을 저장하는 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "531", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "exerciseLogDTO", description = "운동 기록 저장 데이터")
    public ResponseEntity<HttpStatus> createExerciseLog(@RequestBody SummaryDTO.ExerciseLogDTO exerciseLogDTO) {
        exerciseService.createExerciseLog(exerciseLogDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    // 2. 사용자별 전체 운동 기록 조회
//    @GetMapping("/user/{uuid}")
//    public ResponseEntity<List<ExerciseLogDTO>> getLogsByUser(@PathVariable String uuid) {
//        return ResponseEntity.ok(exerciseLogService.getExerciseLogsByUser(uuid));
//    }

    // 3. 운동 기록 단일 조회 (ID 기반)
    @GetMapping("/logs/{id}")
    @Operation(summary = "운동요약 DB 연동 확인", description = "운동요약 entity, dto 연동 확인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "468", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "회원 아이디", example = "2025061401")
    public ResponseEntity<SummaryDTO.ExerciseLogDTO> getExerciseLogById(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getExerciseLogById(id), HttpStatus.OK);
    }

//    // 4. 운동 기록 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLog(@PathVariable int id) {
//        exerciseLogService.deleteExerciseLog(id);
//        return ResponseEntity.noContent().build();
//    }

    // SUMMARY ----------------------------------
    @GetMapping("/summary/{userUuid}")
    @Operation(summary = "운동요약 DB 연동 확인", description = "운동요약 entity, dto 연동 확인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "468", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "userUuid", description = "회원 아이디", example = "2025061401")
    public ResponseEntity<SummaryDTO> getSummaryByUser(@PathVariable String userUuid) {
        return new ResponseEntity<>(exerciseService.getSummaryByUser(userUuid), HttpStatus.OK);
    }


    // Objective -----------------------------
   /* @GetMapping("/objective/{id}")
    @Operation(summary = "목표설정 DB 연동 확인", description = "목표설정 entity, dto 연동 확인")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "470", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "회원 아이디", example = "1")
    public ResponseEntity<ObjectiveDTO> getObjectiveById(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getObjectiveById(id), HttpStatus.OK);
    }*/

    @PostMapping("/objective/created")
    @Operation(summary = "목표 생성", description = "목표 정보를 저장합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "532", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
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

    // ExerciseMonthlyTime ---------------------------------
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
    public ResponseEntity<List<MonthlyExerciseDTO>> getMonthlyExerciseTime(
        @RequestParam LocalDateTime timeMonth,
        @PathVariable String userUuid
    ) {
        List<MonthlyExerciseDTO> result = exerciseService.getMonthlyExerciseTime(timeMonth, userUuid);
        return ResponseEntity.ok(result);
    }




}
