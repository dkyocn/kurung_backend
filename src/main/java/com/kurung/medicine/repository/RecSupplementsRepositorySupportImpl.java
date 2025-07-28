package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QRecommendedSupplementsEntity.recommendedSupplementsEntity;

import com.kurung.medicine.entity.RecommendedSupplementsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecSupplementsRepositorySupportImpl implements RecSupplementsRepositorySupport {
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<RecommendedSupplementsEntity> getRecSuppById(int mediInterId){
    return jpaQueryFactory.selectFrom(recommendedSupplementsEntity)
        .where(recommendedSupplementsEntity.medicineInteraction.mediInterId.eq(mediInterId))
        .fetch();
  }
}
