package com.kurung.medicine.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecSupplementsRepositorySupportImpl implements RecSupplementsRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;
}
