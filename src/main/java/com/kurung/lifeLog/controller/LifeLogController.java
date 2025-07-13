package com.kurung.lifeLog.controller;

import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.dto.MonthlyLifeLogDTO;
import com.kurung.lifeLog.service.LifeLogService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  // 라이프 로그 단일 조회
  @GetMapping("/{id}")
  @Operation(summary = "라이프 로그 단일 조회", description = "라이프 로그 상세보기용 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "473", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "라이프로그 아이디", example = "1")
  public ResponseEntity<LifeLogDTO> getLifeLogById(@PathVariable int id){
    return new ResponseEntity<>(lifeLogService.getLifeLogById(id), HttpStatus.OK);
  }

  // 라이프 로그 리스트 조회
  @GetMapping("/lifeLogList")
  @Operation(summary = "라이프 로그 리스트 조회", description = "라이프 로그 리스트를 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  public ResponseEntity<List<LifeLogDTO>> getLifeLogList(@RequestParam String userUuid, @RequestParam String date) {
    List<LifeLogDTO> lifeLogList = lifeLogService.getLifeLogList(userUuid, date);
    return new ResponseEntity<>(lifeLogList, HttpStatus.OK);
  }

  // 라이프 로그 생성
  @PostMapping("/create")
  @Operation(summary = "라이프 로그 저장", description = "새 라이프 로그를 등록할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "536", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> createLifelog(@RequestBody LifeLogDTO lifeLogDTO) {
    lifeLogService.createLifelog(lifeLogDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 라이프 로그 수정
  @PostMapping("/update")
  @Operation(summary = "라이프 로그 수정", description = "라이프 로그를 수정할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "537", description = "수정 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<HttpStatus> updateLifelog(@RequestBody LifeLogDTO lifeLogDTO) {
    lifeLogService.updateLifeLog(lifeLogDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 라이프 로그 삭제
  @DeleteMapping("/{id}")
  @Operation(summary = "라이프 로그 삭제", description = "라이프 로그를 삭제할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content= @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "538",description = "삭제 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name="id",description = "삭제할 라이프 로그 아이디", example = "1")
  public ResponseEntity<HttpStatus> deleteLifelog(@PathVariable int id) {
    lifeLogService.deleteLifeLogById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 월간 리포트 조회
  @GetMapping("/monthly")
  @Operation(summary = "월간리포트 조회", description = "월간리포트 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  public ResponseEntity<MonthlyLifeLogDTO> getMonthlyLifeLog(@RequestParam String userUuid, @RequestParam String date) {
    return new ResponseEntity<>(lifeLogService.getMonthlyLifeLog(userUuid, date), HttpStatus.OK);
  }
}
