package com.kurung.exercise.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.common.entity.BaseEntity;
import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.user.dto.UserDTO;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoutinesDTO {

  protected int routinesId;
  protected String title;
  protected String routineLevel;
  protected String place;
  protected String videoUrl;
  protected LocalDateTime savedDate;
  protected UserDTO user;

  @Builder(builderMethodName = "toRoutinesBuilder", builderClassName = "toRoutinesBuilder")
  public RoutinesDTO(RoutinesEntity entity) {
    this.routinesId = entity.getRoutinesId();
    this.title = entity.getTitle();
    this.routineLevel = entity.getRoutineLevel();
    this.place = entity.getPlace();
    this.videoUrl = entity.getVideoUrl();
    this.savedDate = entity.getSavedDate();
    this.user = entity.getUser() != null
        ? UserDTO.toUserBuilder().userEntity(entity.getUser()).build()
        : null;
  }
}
