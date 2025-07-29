package com.kurung.lifeLog.dto;

import com.kurung.lifeLog.entity.MonthlyHabitMissionsEntity;
import com.kurung.missions.dto.HabitRecDTO;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.catalina.User;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyHabitMissionsDTO {
  @Schema(description = "월 별 습관 미션 ID")
  protected int monthlyHabitId;
  @Schema(description = "사용자 uuid")
  protected UserDTO user;
  @Schema(description = "습관 미션 해당 월")
  protected LocalDateTime monthlyHabitDate;
  @Schema(description = "습관 미션 ID")
  protected HabitRecDTO habitRecDTO;

  @Builder(builderMethodName = "toMonthlyHabitMissionsBuilder", builderClassName = "toMonthlyHabitMissionsBuilder")
  public MonthlyHabitMissionsDTO(MonthlyHabitMissionsEntity monthlyHabitMissionsEntity){
    this.monthlyHabitId = monthlyHabitMissionsEntity.getMonthlyHabitId();
    this.user = UserDTO.toUserBuilder().userEntity(monthlyHabitMissionsEntity.getUser()).build();
    this.monthlyHabitDate = monthlyHabitMissionsEntity.getMonthlyHabitDate();
    this.habitRecDTO = monthlyHabitMissionsEntity.getHabitRecId() != null ? HabitRecDTO.toHabitRecBuilder().habitRecEntity(monthlyHabitMissionsEntity.getHabitRecId()).build() : null;
  }

}
