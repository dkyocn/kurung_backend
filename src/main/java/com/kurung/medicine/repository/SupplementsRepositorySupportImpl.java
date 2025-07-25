package com.kurung.medicine.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SupplementsRepositorySupportImpl {

  private final JPAQueryFactory jpaQueryFactory;
//
//  public SupplementsEntity getSuppById(int suppId) {
//    return jpaQueryFactory.selectFrom(supplementsEntity)
//        .where(supplementsEntity.suppId.eq(suppId))
//        .fetchOne();
//  }
}
