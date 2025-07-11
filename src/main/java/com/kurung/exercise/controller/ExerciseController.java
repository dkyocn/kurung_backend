package com.kurung.exercise.controller;

import com.kurung.exercise.dto.ExerciseDTO;
import com.kurung.exercise.dto.ObjectiveDTO;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.exercise.dto.SummaryDTO;
import com.kurung.exercise.entity.ExerciseEntity;
import com.kurung.exercise.service.ExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
//
//    // 2. 사용자별 전체 운동 기록 조회
//    @GetMapping("/user/{uuid}")
//    public ResponseEntity<List<ExerciseLogDTO>> getLogsByUser(@PathVariable String uuid) {
//        return ResponseEntity.ok(exerciseLogService.getExerciseLogsByUser(uuid));
//    }

    // 3. 운동 기록 단일 조회 (ID 기반)
    @GetMapping("/logs/{id}")
    public ResponseEntity<SummaryDTO.ExerciseLogDTO> getLogById(@PathVariable int id) {
        return ResponseEntity.ok(exerciseService.getExerciseLogById(id));
    }


//    // 4. 운동 기록 삭제
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLog(@PathVariable int id) {
//        exerciseLogService.deleteExerciseLog(id);
//        return ResponseEntity.noContent().build();
//    }

    // SUMMARY ----------------------------------
    @GetMapping("/summary")
    public ResponseEntity<SummaryDTO> getSummaryByUser(@RequestParam String uuid) {
        return ResponseEntity.ok(exerciseService.getSummaryByUser(uuid));
    }

    // Objective -----------------------------
    @GetMapping("/objective/{id}")
    @Operation(summary = "목표설정 DB 연동 확인", description = "목표설정 entity, dto 연동 확인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "418", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "회원 아이디", example = "1")
    public ResponseEntity<ObjectiveDTO> getObjectiveById(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getObjectiveById(id), HttpStatus.OK);
    }

    // Routines ------------------------------
    @GetMapping("/routines/{id}")
    public ResponseEntity<RoutinesDTO> getRoutinesById(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getRoutinesById(id),HttpStatus.OK);
    }

    // Exercise ---------------------------------
    @GetMapping("/exercise/{id}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable int id) {
        return new ResponseEntity<>(exerciseService.getExerciseById(id), HttpStatus.OK);
    }



}
