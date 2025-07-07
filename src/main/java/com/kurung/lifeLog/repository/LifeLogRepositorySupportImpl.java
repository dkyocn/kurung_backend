package com.kurung.lifeLog.repository;

import com.kurung.lifeLog.entity.LifeLogEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LifeLogRepositorySupportImpl implements LifeLogRepositorySupport {

  private final JPAQueryFactory queryFactory;


}
