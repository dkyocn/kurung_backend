package com.kurung.stressrelief.repository;

import static com.kurung.stressrelief.entity.QStressReliefEntity.stressReliefEntity;

import com.kurung.stressrelief.entity.StressReliefEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;



@Repository
@RequiredArgsConstructor
public class StressReliefRepositorySupportImpl implements StressReliefRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public StressReliefEntity getStressReliefById(int id) {
        return jpaQueryFactory
                .selectFrom(stressReliefEntity)
                .where(stressReliefEntity.stressReliefId.eq(id))
                .fetchOne();
    }

    @Override
    public Page<StressReliefEntity> getStressReliefByPage(Pageable pageable, String keyword) {

        List<StressReliefEntity> stressReliefEntities = jpaQueryFactory.selectFrom(stressReliefEntity)
            .where(containKeyword(keyword))
            .orderBy(stressReliefEntity.stressReliefTitle.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = jpaQueryFactory.select(stressReliefEntity.count())
            .from(stressReliefEntity)
            .where(containKeyword(keyword))
            .fetchOne();

        return new PageImpl<>(stressReliefEntities, pageable, count);
    }

    BooleanExpression containKeyword(String keyword) {
        return StringUtils.isNotBlank(keyword) ? stressReliefEntity.stressReliefTitle.contains(keyword) : null;
    }
}
