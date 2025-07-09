package com.kurung.stressrelief.repository;

import com.kurung.stressrelief.entity.StressReliefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //이 인터페이스가 DB 작업용임을 스프링에게 알려줘 (빈 등록)
public interface StressReliefRepository extends JpaRepository<StressReliefEntity, Integer>, StressReliefRepositorySupport { //스트레스 해소 테이블(TB_STRESS_RELIEF)"에 접근하는 Repository 인터페이스
}

//StressReliefEntity(= 스트레스 해소 테이블)와 연결된 **레포지토리(Repository, DB 다루는 인터페이스)**를 정의한 것.

//클래스(Class)는 ‘설계도 + 실제 구현체’
//인터페이스(Interface)는 ‘설계도만 있음, 실제 내용은 없음’

//JpaRepository<엔티티, 기본키 타입> => 데이터를 DB에서 꺼내거나 저장하는 기본 기능을 자동으로 제공해주는 인터페이스
//StressReliefRepositorySupport => 내가 따로 복잡한 쿼리를 직접 짜고 싶을 때 사용하는 인터페이스




