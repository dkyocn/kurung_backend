package com.kurung.lifeLog.service;

import com.kurung.lifeLog.dto.LifeLogDTO;
import com.kurung.lifeLog.dto.MonthlyLifeLogDTO;
import java.util.List;

public interface LifeLogService {
  // 라이프 로그 단일 조회
  LifeLogDTO getLifeLogById(int id);
  // 라이프 로그 한 달 조회
  List<LifeLogDTO> getLifeLogList(String userUuid, String date);
  // 라이프 로그 생성
  void createLifelog(LifeLogDTO lifeLogDTO);
  // 라이프 로그 수정
  void updateLifeLog(LifeLogDTO lifeLogDTO);
  // 라이프 로그 삭제
  void deleteLifeLogById(int id);
  // 월간 리포트 조회
  MonthlyLifeLogDTO getMonthlyLifeLog(String userUuid, String date);
}
