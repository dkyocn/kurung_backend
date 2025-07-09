package com.kurung.healthReport.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.healthReport.entity.HealthReportEntity;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HealthReportDTO extends BaseDTO {
  @Schema(description = "건강 리포트 아이디", example = "1")
  protected int reportId;
  @Schema(description = "건강상태 요약", example = "블라블라블라블라")
  protected String healthStatus;
  @Schema(description = "건강 리포트 해당 월", example = "2025-06-01")
  protected Date reportMonth;
  @Schema(description = "목표 대비 진행률", example = "76")
  protected float progressRate;
  @Schema(description = "월간 점수", example = "84")
  protected int monthlyScore;
  @Schema(description = "리포트 PDF 저장 위치")
  protected String reportPdfPath;
  @Schema(description = "사용자 정보")
  protected UserDTO user;

  @Builder(builderMethodName = "toHealthReportBuilder",builderClassName = "toHealthReportBuilder")
  public HealthReportDTO(HealthReportEntity healthReportEntity) {
    this.reportId = healthReportEntity.getReportId();
    this.healthStatus = healthReportEntity.getHealthStatus();
    this.reportMonth = healthReportEntity.getReportMonth();
    this.progressRate = healthReportEntity.getProgressRate();
    this.monthlyScore = healthReportEntity.getMonthlyScore();
    this.reportPdfPath = healthReportEntity.getReportPdfPath();
    this.user = UserDTO.toUserBuilder()
        .userEntity(healthReportEntity.getUser())
        .build();
  }
}
