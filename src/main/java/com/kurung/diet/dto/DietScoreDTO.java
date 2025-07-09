package com.kurung.diet.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.diet.entity.DietEntity;
import com.kurung.diet.entity.DietScoreEntity;
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
public class DietScoreDTO {

  @Schema(description = "식단 점수 아이디", example = "1")
  protected int scoreId;
  @Schema(description = "식단 날짜", example = "20250614")
  protected Date dietDate;
  @Schema(description = "식단 점수", example = "58.9")
  protected float dietScore;
  @Schema(description = "사용자 정보")
  protected UserDTO user;

  @Builder(builderMethodName = "toDietScoreBuilder", builderClassName = "toDietScoreBuilder")
  public DietScoreDTO(DietScoreEntity dietScoreEntity) {
    this.scoreId = dietScoreEntity.getScoreId();
    this.dietDate = dietScoreEntity.getDate();
    this.dietScore = dietScoreEntity.getDietScore();
    this.user = UserDTO.toUserBuilder()
        .userEntity(dietScoreEntity.getUser())
        .build();
  }
}
