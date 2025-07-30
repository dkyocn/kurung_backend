package com.kurung.diagnosis.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO extends BaseDTO {

  @Schema(description = "응답", example = "두통이 심합니다.")
  protected String textAnswer;
  @Schema(description = "문항 정보")
  protected int questionId;
  @Schema(description = "선지 정보")
  protected OptionDTO option;
  @Schema(description = "사용자 정보")
  protected UserDTO user;
}
