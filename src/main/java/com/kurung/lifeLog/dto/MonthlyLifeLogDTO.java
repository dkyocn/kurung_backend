package com.kurung.lifeLog.dto;

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
  protected int monthlyLifeLogId;           	// 월간 라이프로그 고유 ID
  protected String month;                      	// 해당 월 (예: 7)
  protected String monthlySummary;         // 월간 요약 텍스트
  protected UserDTO user;                   	// 사용자 정보


  protected List<LifeLogDTO> lifeLogList;   // 해당 월의 일간 라이프로그 목록
  protected double avgSleepTime;               	// 평균 수면 시간 (단위: 시간)
  protected int countLifeLog;               	// 총 라이프로그 개수
  // 감정별 횟수
  protected int countHappy;		// 행복함
  protected int countCalm;		// 평온함
  protected int countTired;		// 피곤함
  protected int countSad;			// 슬픔
  protected int countAngry;		// 화남
  protected int countAnxious;		// 불안함
  protected int countExcited;		// 신남
  protected int countDepressed;	// 우울함

  // 습관 미션 추천
  protected List<MonthlyHabitMissionsDTO> habitMissions;  // 해당 월의 습관 미션 추천

  @Builder(builderMethodName = "toMonthlyLifeLogBuilder", builderClassName = "toLifeLogBuilder")
  public MonthlyLifeLogDTO(MonthlyLifeLogEntity MonthlylifeLogEntity, List<LifeLogDTO> lifeLogList, List<MonthlyHabitMissionsDTO> habitMissions, int avgSleepTime, int countLifeLog, int countHappy, int countCalm, int countTired, int countSad, int countAngry,
      int countAnxious, int countExcited, int countDepressed) {
    this.monthlyLifeLogId = MonthlylifeLogEntity.getMonthlyLifeLogId();
    this.month = MonthlylifeLogEntity.getMonth();
    this.monthlySummary = MonthlylifeLogEntity.getMonthlySummary();
    this.lifeLogList = lifeLogList;
    this.habitMissions = habitMissions;
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
