package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QRecommendedSupplementsEntity.recommendedSupplementsEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecSupplementsRepositorySupportImpl implements RecSupplementsRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;
}
