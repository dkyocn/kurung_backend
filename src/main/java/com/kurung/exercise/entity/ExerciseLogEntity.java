package com.kurung.exercise.entity;

import com.kurung.exercise.dto.SummaryDTO.ExerciseLogDTO;
import com.kurung.user.dto.UserDTO;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_EXERCISE_LOGS")
@EntityListeners(value = {AuditingEntityListener.class})
public class ExerciseLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "EXERCISE_LOGS_ID")
  private int exerciseLogsId;

  @Column(name = "PRE_CONDITION", nullable = false)
  private String preCondition;

  @Column(name = "DURATION", nullable = false)
  private int duration;

  @Column(name = "INTENSITY", nullable = false)
  private String intensity;

  @Column(name = "CALORIES")
  private int calories;

  @Column(name = "HEART_RATE")
  private int heartRate;

  @Column(name = "SET_COUNT")
  private int setCount;

  @Column(name = "REP_COUNT")
  private int repCount;

  @Column(name = "BODY_CONDITION")
  private String bodyCondition;

  @Column(name = "POST_FEELING")
  private String postFeeling;

  @Column(name = "PHYSICAL_NOTE")
  private String physicalNote;

  @Column(name = "MEMO")
  private String memo;

  @Column(name = "EXERCISE_DATE", nullable = false)
  private LocalDateTime exerciseDate;

  @CreatedDate
  @Column(name = "CREATED_AT", nullable = false)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "EXERCISE_ID")
  private ExerciseEntity exercise;

  @Builder(builderMethodName = "createExerciseLogBuilder", builderClassName = "createExerciseLogBuilder")
  public ExerciseLogEntity(ExerciseLogDTO exerciseLogDTO, UserDTO userDTO,
      ExerciseEntity exerciseEntity) {
    this.exerciseLogsId = exerciseLogDTO.getExerciseLogsId();
    this.preCondition = exerciseLogDTO.getPreCondition();
    this.duration = exerciseLogDTO.getDuration();
    this.intensity = exerciseLogDTO.getIntensity();
    this.calories = exerciseLogDTO.getCalories();
    this.heartRate = exerciseLogDTO.getHeartRate();
    this.setCount = exerciseLogDTO.getSetCount();
    this.repCount = exerciseLogDTO.getRepCount();
    this.bodyCondition = exerciseLogDTO.getBodyCondition();
    this.postFeeling = exerciseLogDTO.getPostFeeling();
    this.physicalNote = exerciseLogDTO.getPhysicalNote();
    this.memo = exerciseLogDTO.getMemo();
    this.exerciseDate = exerciseLogDTO.getExerciseDate();
    this.user = UserEntity.createUserBuilder().userDTO(userDTO).build();

    // ExerciseDTO를 ExerciseEntity로 변환
    this.exercise = exerciseEntity;
  }

  public void updateFromDTO(ExerciseLogDTO dto) {
    // 필요한 필드만 업데이트
    this.preCondition = dto.getPreCondition();
    this.duration = dto.getDuration();
    this.intensity = dto.getIntensity();
    this.calories = dto.getCalories();
    this.heartRate = dto.getHeartRate();
    this.setCount = dto.getSetCount();
    this.repCount = dto.getRepCount();
    this.bodyCondition = dto.getBodyCondition();
    this.postFeeling = dto.getPostFeeling();
    this.physicalNote = dto.getPhysicalNote();
    this.memo = dto.getMemo();
    this.exerciseDate = dto.getExerciseDate();
  }

  public void setExercise(ExerciseEntity exercise) {
    this.exercise = exercise;
  }

}
