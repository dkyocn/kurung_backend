package com.kurung.medicine.controller;

import com.kurung.medicine.dto.MedicineInteractionDTO;
import com.kurung.medicine.dto.SubstanceDTO;
import com.kurung.medicine.service.MedicineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "DIET API TEST", description = "식단 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/medicine")
public class MedicineController {

  private final MedicineService medicineService;

  @GetMapping("/result")
  @Operation(summary = "상호작용 결과 조회", description = "약물 상호작용 결과를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "488", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<List<MedicineInteractionDTO>> getInteractionResult() {
    return new ResponseEntity<>(medicineService.getInteractionResult(), HttpStatus.OK);
  }

  @GetMapping("/recSupp")
  @Operation(summary = "추천 영양제 조회", description = "추천받은 영양제를 조회할 때 사용하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "489", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  })
  public ResponseEntity<List<SubstanceDTO>> getRecSupplements() {
    return new ResponseEntity<>(medicineService.getRecSupplements(), HttpStatus.OK);
  }
}
