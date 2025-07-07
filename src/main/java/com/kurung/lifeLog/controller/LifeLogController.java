package com.kurung.lifeLog.controller;

import com.kurung.lifeLog.service.LifeLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "LIFELOG API TEST", description = "라이프 로그 테스트코드입니다.")
@RequestMapping("/api/v1/kurung/lifeLogs")
public class LifeLogController {

  private final LifeLogService lifeLogService;

}
