package com.kurung.healthinfo.dto;

import com.kurung.healthinfo.entity.HealthInfoEntity;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class HealthInfoDTO {

  @Schema(description = "건강 정보 고유 ID", example = "1")
  protected int healthinfoId ;
  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  protected UserDTO userDTO;
  @Schema(description = "신장 (cm)", example = "170.5")
  protected float  height;
  @Schema(description = "체중 (kg)", example = "65.2")
  protected float   weight;
  @Schema(description = "체지방률 (%)", example = "18.5")
  protected float   bodyfatpercent;
  @Schema(description = "BMI", example = "22.3")
  protected float   bmi;
  @Schema(description = "메모", example = "건강 회복")
  protected String  memo;
  @Schema(description = "최종 수정일자", example = "2025-07-14")
  protected LocalDateTime updatedAt;

  @Builder(builderMethodName = "toHealthInfoBuilder", builderClassName = "toHealthInfoBuilder")
  public HealthInfoDTO(HealthInfoEntity entity) {
    this.healthinfoId = entity.getHealthinfoId();
    this.userDTO = entity.getUser() != null ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build() : null;
    this.height = entity.getHeight();
    this.weight = entity.getWeight();
    this.bodyfatpercent = entity.getBodyfatpercent();
    this.bmi = entity.getBmi();
    this.memo = entity.getMemo();
    this.updatedAt = entity.getUpdatedAt();
  }
}
