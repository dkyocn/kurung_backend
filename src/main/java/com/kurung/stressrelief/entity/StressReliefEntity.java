package com.kurung.stressrelief.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_STRESS_RELIEF")
public class StressReliefEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STRESS_RELIEF_ID")
    private int stressReliefId;

    @Column(name = "STRESS_RELIEF_TITLE", nullable = false)
    private String stressReliefTitle;

    @Column(name = "STRESS_RELIEF_EFFECT")
    private String stressReliefEffect;

    @Column(name = "STRESS_RELIEF", nullable = false)
    private String stressRelief;
}



