package com.kurung.diagnosis.controller;

import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.service.DiagnosisService;
import com.kurung.diet.dto.DietDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "DIET API TEST", description = "건강진단 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/diagnosis")
public class DiagnosisController {

  private final DiagnosisService diagnosisService;

  @GetMapping("/{score}")
  @Operation(summary = "식단 단일 조회", description = "하나의 식단을 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "418", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "score", description = "건강점수", example = "85")
  public ResponseEntity<DiagnosisDTO> getDiagnosisById(@PathVariable int score) {
    return new ResponseEntity<>(diagnosisService.getDiagnoisById(score), HttpStatus.OK);
  }
}
