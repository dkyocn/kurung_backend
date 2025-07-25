package com.kurung.diet.controller;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.dto.DietScoreDTO;
import com.kurung.diet.dto.FoodDTO;
import com.kurung.diet.enumeration.MEAL;
import com.kurung.diet.service.DietService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
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
@Tag(name = "DIET API TEST", description = "식단 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/diet")
public class DietController {

  private final DietService dietService;

  @GetMapping("")
  @Operation(summary = "식단 단일 조회", description = "하나의 식단을 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "463", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameters({
      @Parameter(name = "currentDate", description = "오늘 날짜", example = "2025-05-19T00:00:00"),
      @Parameter(name = "meal", description = "아침, 점심, 저녁", example = "DINNER")
  })
  public ResponseEntity<DietDTO> getDietById(@RequestParam LocalDateTime currentDate, @RequestParam MEAL meal) {
    return new ResponseEntity<>(dietService.getCurrentDiet(currentDate,meal), HttpStatus.OK);
  }

  @GetMapping("/score/{id}")
  @Operation(summary = "식단 점수 조회", description = "식단 점수를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "464", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "식단 점수 아이디", example = "1")
  public ResponseEntity<DietScoreDTO> getDietScoreById(@PathVariable int id) {
    return new ResponseEntity<>(dietService.getDietScoreById(id), HttpStatus.OK);
  }

  @GetMapping("/score")
  @Operation(summary = "식단 점수 한달 조회", description = "식단 점수 한달치를 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  @Parameters({
      @Parameter(name = "currentDate", description = "오늘 날짜", example = "2025-05-19T00:00:00"),
  })
  public ResponseEntity<List<DietScoreDTO>> getDietScoreMonthList(@RequestParam LocalDateTime currentDate) {
    return new ResponseEntity<>(dietService.getDietScoreMonthList(currentDate), HttpStatus.OK);
  }

  @PostMapping("")
  @Operation(summary = "식단 저장", description = "식단을 저장할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "저장 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "526", description = "저장 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "dietDTO", description = "식단 저장 데이터")
  public ResponseEntity<HttpStatus> createDiet(@RequestBody DietDTO dietDTO) {
    dietService.createDiet(dietDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping("/update")
  @Operation(summary = "식단 수정", description = "식단을 수정할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "수정 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "527", description = "수정 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "dietDTO", description = "식단 수정 데이터")
  public ResponseEntity<HttpStatus> updateDiet(@RequestBody DietDTO dietDTO) {
    dietService.updateDiet(dietDTO);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "식단 삭제", description = "식단을 삭제할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "삭제 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "528", description = "삭제 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "삭제할 식단 아이디", example = "1")
  public ResponseEntity<HttpStatus> deleteDietById(@PathVariable int id) {
    dietService.deleteDietById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/food")
  @Operation(summary = "음식 리스트 조회", description = "음식 리스트를 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  @Parameter(name = "keyword", description = "검색어", example = "김치")
  public ResponseEntity<List<FoodDTO>> getFoodListByDietId(@RequestParam(required = false) String keyword) {
    return new ResponseEntity<>(dietService.getFoodList(keyword), HttpStatus.OK);
  }

  @GetMapping("/food/{id}")
  @Operation(summary = "음식 조회", description = "하나의 음식을 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "465", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "음식 아이디", example = "1")
  public ResponseEntity<FoodDTO> getFoodById(@PathVariable int id) {
    return new ResponseEntity<>(dietService.getFoodById(id), HttpStatus.OK);
  }
}
