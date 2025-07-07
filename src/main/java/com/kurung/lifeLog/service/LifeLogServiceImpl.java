package com.kurung.lifeLog.service;

import com.kurung.lifeLog.repository.LifeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LifeLogServiceImpl implements LifeLogService{

  private final LifeLogRepository lifeLogRepository;

}
