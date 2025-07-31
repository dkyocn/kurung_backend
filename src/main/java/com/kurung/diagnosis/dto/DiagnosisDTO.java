package com.kurung.diagnosis.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.diagnosis.enumeration.Category;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDTO extends BaseDTO {

  @Schema(description = "건강정보 요약", example = "양호합니다")
  protected String dianosisSummary;
  @Schema(description = "건강점수", example = "85")
  protected int score;
  @Schema(description = "목표")
  protected List<GoalDTO> goalDTOList;
  @Schema(description = "사용자 정보")
  protected UserDTO user;

  @Getter
  @SuperBuilder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class GoalDTO {

    @Schema(description = "목표 제목", example = "산책")
    protected String goalTitle;
    @Schema(description = "목표 내용", example = "가벼운 산책을 해보세요.")
    protected String goalText;
    @Schema(description = "카테고리", example = "DIET")
    protected Category category;
  }
}
