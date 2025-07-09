package com.kurung.medicine.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.medicine.enumeration.MEDICINE;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubstanceDTO {
  @Schema(description = "영양제 아이디", example = "1")
  protected int substanceId;
  @Schema(description = "영양제 이름", example = "Vitamin D3")
  protected String name;
  @Schema(description = "영양제 한글명", example = "비타민 D3")
  protected String nameKo;
  @Schema(description = "카테고리", example = "비타민")
  protected String category;
  @Schema(description = "제조사", example = "NOW Foods")
  protected String company;
  @Schema(description = "이미지 경로", example = "/images/supp_1.jpg")
  protected String imagePath;
  @Schema(description = "약물 ENUM")
  protected MEDICINE medicine;
}
