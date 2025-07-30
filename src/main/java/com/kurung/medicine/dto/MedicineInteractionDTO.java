package com.kurung.medicine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineInteractionDTO {
  @Schema(description = "상호작용 아이디", example = "1")
  protected int mediInterId;
  @Schema(description = "첫 번째 약물 정보")
  protected SubstanceDTO medicine1;
  @Schema(description = "두 번째 약물 정보")
  protected SubstanceDTO medicine2;
  @Schema(description = "위험성", example = "경미함")
  protected String risk;
  @Schema(description = "상호작용 결과", example = "출혈 위험 증가")
  protected String interResult;
}
