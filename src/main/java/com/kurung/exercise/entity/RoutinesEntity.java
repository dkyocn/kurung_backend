package com.kurung.exercise.entity;


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
}
