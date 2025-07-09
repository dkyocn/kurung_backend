package com.kurung.diagnosis.dto;

import com.kurung.common.dto.BaseDTO;
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
public class QuestionDTO extends BaseDTO {

  @Schema(description = "문항 번호", example = "1")
  protected int questionCode;
  @Schema(description = "카테고리", example = "식습관")
  protected String category;
  @Schema(description = "문항 내용", example = "나는 매일 아침 식사를 챙겨먹는다.")
  protected String questionText;
  @Schema(description = "다중선택 여부", example = "0")
  protected int isMultiple;
  @Schema(description = "서술형 여부", example = "0")
  protected int textOption;
  @Schema(description = "선지 정보")
  protected List<OptionDTO> options;
}
