package com.kurung.exercise.entity;


import com.kurung.common.entity.BaseEntity;
import com.kurung.exercise.dto.RoutinesDTO;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TB_ROUTINES")
public class RoutinesEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROUTINES_ID")
  private int routinesId;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "ROUTINE_LEVEL")
  private String routineLevel;

  @Column(name = "PLACE")
  private String place;

  @Column(name = "VIDEO_URL")
  private String videoUrl;

  @Column(name = "SAVED_DATE", nullable = false)
  private LocalDateTime savedDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID")
  private UserEntity user;

  @Builder(builderMethodName = "createRoutinesBuilder", builderClassName = "createRoutinesBuilder")
  public RoutinesEntity(RoutinesDTO routinesDTO, UserDTO userDTO) {
    this.routinesId = routinesDTO.getRoutinesId();
    this.title = routinesDTO.getTitle();
    this.routineLevel = routinesDTO.getRoutineLevel();
    this.place = routinesDTO.getPlace();
    this.videoUrl = routinesDTO.getVideoUrl();
    this.savedDate = routinesDTO.getSavedDate();
    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build(); // UserDTO -> UserEntity 변환
  }
}
