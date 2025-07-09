package com.kurung.stressrelief.repository;

import com.kurung.stressrelief.entity.StressReliefEntity;

public interface StressReliefRepositorySupport {

    StressReliefEntity getStressReliefById(int id); //id 값을 기준으로 스트레스 해소법 하나를 조회하는 메서드의 선언
}

//StressReliefEntity: DB에서 꺼낸 한 건의 데이터 (Java 객체 형태)
//getStressReliefById(int id): "id가 몇 번인 데이터를 요청하는" 의미









