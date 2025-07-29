package com.kurung.medicine.repository;

import static com.kurung.medicine.entity.QMedicineEntity.medicineEntity;

import com.kurung.medicine.entity.MedicineEntity;
import com.kurung.medicine.entity.MedicineInteractionEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MedicineRepositorySupportImpl implements MedicineRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<MedicineEntity> getMedicineList(String keyword) {
    return jpaQueryFactory.selectFrom(medicineEntity)
        .where(containKeyword(keyword))
        .fetch();
  }

  // keyword 유무 조건 적용
  private BooleanExpression containKeyword(String keyword) {
    return StringUtils.isNotBlank(keyword) ? medicineEntity.mediNameKo.contains(keyword) : null;
  }

}
