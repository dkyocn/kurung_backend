package com.kurung.community.controller;

import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.service.CommunityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
