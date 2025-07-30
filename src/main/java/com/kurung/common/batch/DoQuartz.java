package com.kurung.common.batch;

import com.kurung.missions.service.MissionsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DoQuartz {

  private final MissionsService missionsService;

  /**
   * quartz 설정
   * 00:00에 한번씩 job 수행
   */
  @Scheduled(cron = "0 0 0 * * *")
//  @Scheduled(cron = "*/10 * * * * *")
  public void doCreateQuartz() {
    log.info("doCreateQuartz");
  }
}
