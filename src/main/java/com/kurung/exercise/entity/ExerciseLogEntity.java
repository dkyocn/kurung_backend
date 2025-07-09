package com.kurung.exercise.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


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
    @Column(name = "DURATION", nullable = false)
    private int duration;
    @Column(name = "INTENSITY", nullable = false)
    private String intensity;
    @Column(name = "CALORIES", nullable = false)
    private int calories;
    @Column(name = "HEART_RATE")
    private int heartRate;
    @Column(name = "PRE_CONDITION")
    private String condition;
    @Column(name = "PHYSICAL_NOTE")
    private String physicalNote;
    @Column(name = "MEMO")
    private String memo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false)
    private Date createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_UUID")
    private UserEntity user;

}
