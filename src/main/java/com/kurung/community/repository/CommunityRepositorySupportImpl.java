package com.kurung.community.repository;

import static com.kurung.community.entity.QCommunityEntity.communityEntity;

import com.kurung.common.enumeration.HealthType;
import com.kurung.community.entity.CommunityEntity;
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
public class CommunityRepositorySupportImpl implements CommunityRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public CommunityEntity getCommunityById(int id) {
    return queryFactory.selectFrom(communityEntity)
        .where(communityEntity.communityId.eq(id))
        .fetchOne();
  }

  @Override
  public Page<CommunityEntity> getCommunityByPage(Pageable pageable, String keyword, HealthType healthType) {

    List<CommunityEntity> fetch = queryFactory.selectFrom(communityEntity)
        .where(containKeyword(keyword), equalType(healthType))
        .orderBy(communityEntity.communityId.desc())
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long count = queryFactory.select(communityEntity.count())
        .from(communityEntity)
        .where(containKeyword(keyword), equalType(healthType))
        .fetchOne();

    return new PageImpl<>(fetch, pageable, count);
  }

  BooleanExpression equalType(HealthType healthType) {
    return healthType != null ? communityEntity.category.eq(healthType) : null;
  }

  BooleanExpression containKeyword(String keyword) {
    return StringUtils.isNotBlank(keyword) ? communityEntity.title.contains(keyword) : null;
  }
}
