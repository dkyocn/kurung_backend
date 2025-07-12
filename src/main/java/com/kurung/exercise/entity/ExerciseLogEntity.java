package com.kurung.exercise.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_EXERCISE_LOGS")
public class ExerciseLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXERCISE_LOGS_ID")
    private int exerciseLogsId;

    @Column(name = "EXERCISE_ID", nullable = false)
    private int exerciseId;

    @Column(name = "PRE_CONDITION", nullable = false)
    private String preCondition;

    @Column(name = "DURATION", nullable = false)
    private int duration;

    @Column(name = "INTENSITY", nullable = false)
    private String intensity;

    @Column(name = "CALORIES")
    private Integer calories;

    @Column(name = "HEART_RATE")
    private Integer heartRate;

    @Column(name = "SET_COUNT")
    private Integer setCount;

    @Column(name = "REP_COUNT")
    private Integer repCount;

    @Column(name = "BODY_CONDITION")
    private String bodyCondition;

    @Column(name = "POST_FEELING")
    private String postFeeling;

    @Column(name = "PHYSICAL_NOTE")
    private String physicalNote;

    @Column(name = "MEMO")
    private String memo;

    @CreatedDate
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_UUID", nullable = false)
    private UserEntity user;

}
