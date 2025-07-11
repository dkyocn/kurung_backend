package com.kurung.exercise.dto;

import com.kurung.exercise.entity.RoutinesEntity;
import com.kurung.user.dto.UserDTO;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RoutinesDTO {

  private int routinesId;
  private String title;
  private String routineLevel;
  private String place;
  private String videoUrl;
  private LocalDateTime savedDate;
  private UserDTO user;

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
