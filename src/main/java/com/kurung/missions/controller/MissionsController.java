package com.kurung.missions.controller;

import com.kurung.common.enumeration.HealthType;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.service.MissionsService;
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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/missions")  // ✅ missions로 변경됨
@Tag(name = "MISSIONS API TEST", description = "미션 API 테스트용입니다.")
public class MissionsController {

  private final MissionsService missionsService;

  @GetMapping("/list")
  @Operation(summary = "미션 단일 조회", description = "하나의 미션을 ID로 조회하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "418", description = "조회"
          + ""
          + ""
          + ""
          + " 실패", content = @Content(mediaType = "application/json"))
  })
  public ResponseEntity<List<MissionsDTO>> getMissionById() {
    return new ResponseEntity<>(missionsService.getMissionsList(), HttpStatus.OK);
  }


  @GetMapping("/today")
  @Operation(summary = "오늘 미션 리스트 조회", description = "오늘 미션을 조회하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json"))
  @Parameter(name = "userUuid", description = "사용자 UUID", example = "2025061401")
  public ResponseEntity<List<MissionsDTO>> getTodayMissions(@RequestParam String userUuid) {
    return new ResponseEntity<>(missionsService.getTodayMissions(userUuid), HttpStatus.OK);
  }

  @GetMapping("/range")
  @Operation(summary = "미션 한달 조회", description = "미션 한달 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  @Parameters({
      @Parameter(name = "currentDate", description = "기준 날짜", example = "2025-06-01"),
      @Parameter(name = "userUuid", description = "사용자 UUID", example = "2025061401")
  })
  public ResponseEntity<List<MissionsDTO>> getMissionMonthList(
      @RequestParam LocalDate currentDate,
      @RequestParam HealthType displayType
  ) {
    return new ResponseEntity<>(missionsService.getMissionMonthList(currentDate, displayType), HttpStatus.OK);
  }

}
