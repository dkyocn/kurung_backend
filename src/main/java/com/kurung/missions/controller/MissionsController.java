package com.kurung.missions.controller;

import com.kurung.missions.dto.MissionsDTO;
import com.kurung.missions.service.MissionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

  @GetMapping("/{id}")
  @Operation(summary = "미션 단일 조회", description = "하나의 미션을 ID로 조회하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "418", description = "조회 실패", content = @Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "미션 ID", example = "1")
  public ResponseEntity<MissionsDTO> getMissionById(@PathVariable int id) {
    return new ResponseEntity<>(missionsService.getMissionsById(id), HttpStatus.OK);
  }
}
