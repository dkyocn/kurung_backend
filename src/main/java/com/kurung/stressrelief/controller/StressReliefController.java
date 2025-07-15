package com.kurung.stressrelief.controller;

import com.kurung.stressrelief.dto.StressReliefDTO;
import com.kurung.stressrelief.service.StressReliefService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Tag(name = "STRESSRELIEF API TEST", description = "스트레스해소 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/stressrelief")
public class StressReliefController {

    private final StressReliefService stressreliefService;

    @GetMapping("/{id}")
    @Operation(summary = "스트레스해소 단일 조회", description = "하나의 스트레스해소 조회시 사용하는 API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "418", description = "조회 실패", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    })
    @Parameter(name = "id", description = "스트레스 조회 아이디", example = "1")
    public ResponseEntity<StressReliefDTO> getStressReliefById(@PathVariable int id) {
        return new ResponseEntity<>(stressreliefService.getStressReliefById(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    @Operation(summary = "스트레스해소 페이지 조회", description = "스트레스해소 페이지 조회 시 사용하는 API")
    @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
    @Parameter(name = "keyword", description = "검색어", example = "호흡")
    public ResponseEntity<Page<StressReliefDTO>>  getStressReliefByPage(Pageable pageable, @RequestParam(required = false) String keyword) {
        return new ResponseEntity<>(stressreliefService.getStressReliefByPage(pageable, keyword), HttpStatus.OK);
    }
}







