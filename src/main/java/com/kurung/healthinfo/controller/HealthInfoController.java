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
import java.time.LocalDateTime;
import java.util.List;
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
@Tag(name = "HEALTH INFO API TEST", description = " 건강정보 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/healthinfo")
public class HealthInfoController {

  private final HealthInfoService healthInfoService;

  @GetMapping("/list")
  @Operation(summary = "건강정보 단일 조회", description = "사용자의 건강정보를 조회할 때 사용하는 API")
  public ResponseEntity<HealthInfoDTO> getHealthInfoById(
      @RequestParam String userUuid,
      @RequestParam LocalDateTime targetDate
  ) {
    return new ResponseEntity<>(healthInfoService.getHealthInfoById(userUuid, targetDate), HttpStatus.OK);
  }


}
