package com.kurung.lifeLog.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.lifeLog.entity.LifeLogEntity;
import com.kurung.lifeLog.entity.MonthlyLifeLogEntity;
import com.kurung.user.dto.UserDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyLifeLogDTO {
  private int monthlyLifeLogId;           	// 월간 라이프로그 고유 ID
  private String month;                      	// 해당 월 (예: 7)
  private String monthlySummary;         // 월간 요약 텍스트
  private UserDTO user;                   	// 사용자 정보


  private List<LifeLogDTO> lifeLogList;   // 해당 월의 일간 라이프로그 목록
  private int avgSleepTime;               	// 평균 수면 시간 (단위: 분)
  private int countLifeLog;               	// 총 라이프로그 개수
  // 감정별 횟수
  private int countHappy;		// 행복함
  private int countCalm;		// 평온함
  private int countTired;		// 피곤함
  private int countSad;			// 슬픔
  private int countAngry;		// 화남
  private int countAnxious;		// 불안함
  private int countExcited;		// 신남
  private int countDepressed;	// 우울함

  @Builder(builderMethodName = "toMonthlyLifeLogBuilder", builderClassName = "toLifeLogBuilder")
  public MonthlyLifeLogDTO(MonthlyLifeLogEntity MonthlylifeLogEntity, List<LifeLogDTO> lifeLogList, int avgSleepTime, int countLifeLog, int countHappy, int countCalm, int countTired, int countSad, int countAngry,
      int countAnxious, int countExcited, int countDepressed) {
    this.monthlyLifeLogId = MonthlylifeLogEntity.getMonthlyLifeLogId();
    this.month = MonthlylifeLogEntity.getMonth();
    this.monthlySummary = MonthlylifeLogEntity.getMonthlySummary();
    this.lifeLogList = lifeLogList;
    this.avgSleepTime = avgSleepTime;
    this.countLifeLog = countLifeLog;
    this.countHappy = countHappy;
    this.countCalm = countCalm;
    this.countTired = countTired;
    this.countSad = countSad;
    this.countAngry = countAngry;
    this.countAnxious = countAnxious;
    this.countExcited = countExcited;
    this.countDepressed = countDepressed;
    this.user = UserDTO.toUserBuilder()
        .userEntity(MonthlylifeLogEntity.getUser())
        .build();
  }

}
