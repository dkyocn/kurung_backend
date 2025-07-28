package com.kurung.community.repository;

import com.kurung.community.entity.CommentEntity;

public interface CommentRepositorySupport {

  CommentEntity getComment(int id);
}
