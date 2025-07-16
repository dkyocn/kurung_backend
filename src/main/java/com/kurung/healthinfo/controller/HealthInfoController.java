package com.kurung.healthinfo.controller;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.healthinfo.dto.HealthInfoDTO;
import com.kurung.healthinfo.service.HealthInfoService;
import com.kurung.missions.dto.MissionsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
@Tag(name = "HEALTH INFO API TEST", description = " 건강정보 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/healthinfo")
public class HealthInfoController {

  private final HealthInfoService healthInfoService;

//  @GetMapping("/list")
//  @Operation(summary = "건강정보 전체 조회", description = "사용자의 건강정보 리스트를 조회하는 API")
//  public ResponseEntity<List<HealthInfoDTO>> getHealthInfoList() {
//    return new ResponseEntity<>(healthInfoService.getHealthInfo(), HttpStatus.OK);
//  }


  @GetMapping("/list")
  @Operation(summary = "건강정보 단일 조회", description = "사용자의 건강정보를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "463", description = "조회 실패", content = @Content(mediaType = "application/json"))
  })
  @Parameters({
      @Parameter(name = "userUuid", description = "사용자 UUID", example = "2025061401"),
      @Parameter(name = "targetDate", description = "조회 기준 날짜", example = "2025-07-15")
  })
  public ResponseEntity<HealthInfoDTO> getHealthInfoById(
      @RequestParam String userUuid,
      @RequestParam LocalDateTime targetDate
  ) {
    return new ResponseEntity<>(healthInfoService.getHealthInfoById(userUuid, targetDate), HttpStatus.OK);
  }

  @GetMapping("/month")
  @Operation(summary = "건강정보 한달 조회", description = "건강정보 한달 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  @Parameters({
      @Parameter(name = "currentDate", description = "기준 날짜", example = "2025-06-01"),
      @Parameter(name = "userUuid", description = "사용자 UUID", example = "2025061401")
  })
  public ResponseEntity<List<HealthInfoDTO>> getHealthInfoMonthList(
      @RequestParam LocalDate currentDate,
      @RequestParam String userUuid
  ) {

    return new ResponseEntity<>(healthInfoService.getHealthInfoMonthList(currentDate, userUuid), HttpStatus.OK);
  }

  @PostMapping("/update")
  @Operation(summary = "건강정보 수정", description = "건강정보를 수정할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "526", description = "수정 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })

  @Parameter(name = "healthInfoDTO", description = "건강정보 저장 데이터")
  public ResponseEntity<HttpStatus> updateHealthInfo(@RequestBody HealthInfoDTO healthInfoDTO) {
    healthInfoService.updateHealthInfo(healthInfoDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/create")
  @Operation(summary = "건강정보 저장", description = "새 건강정보를 등록할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "535", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> createHealthInfo(@RequestBody HealthInfoDTO healthInfoDTO) {
    healthInfoService.createHealthInfo(healthInfoDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
