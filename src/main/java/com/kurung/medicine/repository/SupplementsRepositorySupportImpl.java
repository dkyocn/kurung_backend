package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QSupplementsEntity.supplementsEntity;

import com.kurung.medicine.entity.SupplementsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SupplementsRepositorySupportImpl implements SupplementsRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;


}
