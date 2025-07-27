package com.kurung.community.repository;

import com.kurung.community.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer>, CommentRepositorySupport {
}
