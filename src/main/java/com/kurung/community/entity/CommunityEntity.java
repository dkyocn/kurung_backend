package com.kurung.community.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.community.enumeration.CATEGORY;
import com.kurung.user.entity.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_COMMUNITY")
public class CommunityEntity extends BaseEntity {

  @Id
  @Column(name = "COMMUNITY_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int communityId;
  @Column(name = "TITLE", nullable = false)
  private String title;
  @Column(name = "CONTENT", nullable = false)
  private String content;
  @Enumerated(EnumType.STRING)
  @Column(name = "CATEGORY", nullable = false)
  private CATEGORY category;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @Column(name = "COMMENT")
  @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CommentEntity> comment;

}
