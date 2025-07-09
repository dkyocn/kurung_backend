package com.kurung.stressrelief.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_STRESS_RELIEF") //이 클래스는 데이터베이스 테이블과 연결되는 JPA 전용 클래스라고 알려주는 표시
public class StressReliefEntity {

    @Id // 이 필드는 기본키(Primary Key)라는 뜻
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB에서 자동으로 숫자를 1씩 증가시키며 생성해줘
    @Column(name = "STRESS_RELIEF_ID") //DB에서 "STRESS_RELIEF_ID"라는 이름의 컬럼이랑 연결된다는 뜻
    private int stressReliefId;

    @Column(name = "STRESS_RELIEF_TITLE", nullable = false)
    private String stressReliefTitle;

    @Column(name = "STRESS_RELIEF_EFFECT")
    private String stressReliefEffect;

    @Column(name = "STRESS_RELIEF", nullable = false)
    private String stressRelief;
}

//nullable = false => null 허용하지 않음, 즉 반드시 값이 있어야 함


