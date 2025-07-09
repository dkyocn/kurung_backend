package com.kurung.lifeLog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MonthlyLifeLogRepositorySupportImpl implements MonthlyLifeLogRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

}
