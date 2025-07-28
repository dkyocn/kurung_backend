package com.kurung.community.repository;

import static com.kurung.community.entity.QCommentEntity.commentEntity;

import com.kurung.community.entity.CommentEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositorySupportImpl implements CommentRepositorySupport {

  private final JPAQueryFactory queryFactory;

  @Override
  public CommentEntity getComment(int id) {
    return queryFactory.selectFrom(commentEntity)
        .where(commentEntity.commentId.eq(id))
        .fetchOne();
  }
}
