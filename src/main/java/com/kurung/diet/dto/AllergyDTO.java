package com.kurung.diet.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AllergyDTO {

  @Schema(description = "알레르기 아이디", example = "1")
  protected int allergyId;
  @Schema(description = "알레르기 이름", example = "1")
  protected String allergyName;
}
