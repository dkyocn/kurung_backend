package com.kurung.community.repository;

import static com.kurung.community.entity.QCommunityEntity.communityEntity;

import com.kurung.community.dto.CommunityDTO;
import com.kurung.community.entity.CommunityEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
}
