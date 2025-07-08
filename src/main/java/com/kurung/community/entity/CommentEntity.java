package com.kurung.community.entity;

import com.kurung.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_COMMENT")
public class CommentEntity {

  @Id
  @Column(name = "COMMENT_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentId;
  @Column(name = "CONTENT", nullable = false)
  private String content;
  @CreatedDate
  @Column(name = "CREATED_AT")
  private Date createdAt;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @JoinColumn(name = "COMMUNITY_ID")
  @ManyToOne(fetch = FetchType.LAZY)
  private CommunityEntity community;

}
