package com.kurung.community.controller;

import com.kurung.common.enumeration.HealthType;
import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(name = "COMMUNITY API TEST", description = "커뮤니티 보드 API 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/community")
public class CommunityController {

  private final CommunityService communityService;

  @GetMapping("/{id}")
  public ResponseEntity<CommunityDTO> getCommunityById(@PathVariable int id) {
    return new ResponseEntity<>(communityService.getCommunityById(id), HttpStatus.OK);
  }

  @GetMapping("/page")
  @Operation(summary = "커뮤니티 페이지 조회", description = "커뮤니티 페이지를 조회할 때 사용하는 API")
  @ApiResponse(responseCode = "200", description = "조회 성공", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json"))
  @Parameters({
      @Parameter(name = "keyword", description = "검색어", example = "볶음밥"),
      @Parameter(name = "healthType", description = "커뮤니티 카테고리", example = "DIET")
  })
  public ResponseEntity<Page<CommunityDTO>> getCommunityByPage(@RequestParam(name = "keyword", required = false) String keyword,
      @RequestParam(name = "healthType") HealthType healthType,
      Pageable pageable) {
    return new ResponseEntity<>(communityService.getCommunityByPage(pageable, healthType, keyword), HttpStatus.OK);
  }
}
