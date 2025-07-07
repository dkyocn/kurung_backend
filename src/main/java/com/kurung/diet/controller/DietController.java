package com.kurung.diet.controller;

import com.kurung.diet.dto.DietDTO;
import com.kurung.diet.service.DietService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "DIET API TEST", description = "식단 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/diet")
public class DietController {

  private final DietService dietService;

  @GetMapping("/{id}")
  @Operation(summary = "식단 단일 조회", description = "하나의 식단을 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "418", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "식단 아이디", example = "1")
  public ResponseEntity<DietDTO> getDietById(@PathVariable int id) {
    return new ResponseEntity<>(dietService.getDietById(id), HttpStatus.OK);
  }

//    @Parameters({
//            @Parameter(name = "email", description = "이메일", example = "chrome123@naver.com"),
//            @Parameter(name = "password", description = "6자~12자 이내", example = "abcd1234"),
//            @Parameter(name = "companyName", description = "업체명", example = "코리아 시스템"),
//            @Parameter(name = "companyNumber", description = "업체 번호", example = "112233"),
//            @Parameter(name = "companyAddress", description = "업체 주소", example = "인천시 미추홀구 용현동")
//    })
}
