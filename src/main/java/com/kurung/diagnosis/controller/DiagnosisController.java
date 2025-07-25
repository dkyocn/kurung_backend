package com.kurung.diagnosis.controller;

import com.kurung.diagnosis.dto.AnswerDTO;
import com.kurung.diagnosis.dto.DiagnosisDTO;
import com.kurung.diagnosis.dto.QuestionDTO;
import com.kurung.diagnosis.service.DiagnosisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "DIET API TEST", description = "건강진단 API 코드입니다.")
@RequestMapping("/api/v1/kurung/diagnosis")
public class DiagnosisController {

  private final DiagnosisService diagnosisService;

  @GetMapping("/questions")
  @Operation(summary = "건강진단 리스트 조회", description = "질문 리스트를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "458", description = "리스트 조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
    return new ResponseEntity<>(diagnosisService.getAllQuestions(), HttpStatus.OK);
  }

  @GetMapping("/result")
  @Operation(summary = "건강진단 결과 조회", description = "건강진단 결과를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "459", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "userUuid", description = "사용자 UUID")
  public ResponseEntity<DiagnosisDTO> getDiagnosisResult(@RequestParam String userUuid){
    return new ResponseEntity<>(diagnosisService.getDiagnosisResult(userUuid), HttpStatus.OK);
  }

  @PostMapping("/answers")
  @Operation(summary = "사용자 건강진단 응답 리스트 저장", description = "건강진단 응답을 저장할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "응답 저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "521", description = "응답 저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "522", description = "응답 삭제 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))

  })
  public ResponseEntity<HttpStatus> createAnswers(@RequestBody List<AnswerDTO> answerList){
    diagnosisService.createAnswers(answerList);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
