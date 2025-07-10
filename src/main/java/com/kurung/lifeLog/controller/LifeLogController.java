package com.kurung.lifeLog.controller;

import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.service.LifeLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "LIFELOG API TEST", description = "라이프 로그 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/lifeLogs")
public class LifeLogController {

  private final LifeLogService lifeLogService;

  @GetMapping("/{id}")
  @Operation(summary = "라이프 로그 단일 조회", description = "라이프 로그 상세보기용 API")
  @Parameter(name = "id", description = "라이프로그 아이디", example = "1")
  public ResponseEntity<LifeLogDTO> getLifeLogById(@PathVariable int id){
    return new ResponseEntity<>(lifeLogService.getLifeLogById(id), HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<List<LifeLogDTO>> getLifeLogList(@RequestParam String userUuid, @RequestParam String date) {
    List<LifeLogDTO> lifeLogList = lifeLogService.getLifeLogList(userUuid, date);
    return new ResponseEntity<>(lifeLogList, HttpStatus.OK);
  }

}
