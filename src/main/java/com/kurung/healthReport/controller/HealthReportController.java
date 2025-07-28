package com.kurung.healthReport.controller;

import com.kurung.healthReport.dto.HealthReportDTO;
import com.kurung.healthReport.service.HealthReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "HEALTHREPORT API TEST", description = "건강리포트 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/healthReport")
public class HealthReportController {

  private final HealthReportService healthReportService;

  @GetMapping("/report")
  @Operation(summary = "건강리포트 조회", description = "하나의 식단을 조회할 때 사용하는 API")
  @Parameter(name = "reportMonth", description = "건강 리포트 해당 달", example = "20250601")
  public ResponseEntity<HealthReportDTO> getHealthReportByMonth(@RequestParam LocalDateTime reportMonth){
    return new ResponseEntity<>(healthReportService.getHealthReportByMonth(reportMonth), HttpStatus.OK);
  }
}
