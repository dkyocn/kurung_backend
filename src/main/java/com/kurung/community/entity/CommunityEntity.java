package com.kurung.community.entity;

import com.kurung.common.entity.BaseEntity;
import com.kurung.common.enumeration.HealthType;
import com.kurung.community.dto.CommunityDTO;
import com.kurung.user.dto.UserDTO;
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
import java.util.ArrayList;
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
  private HealthType category;
  @JoinColumn(name = "USER_UUID")
  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity user;
  @Column(name = "COMMENT")
  @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private List<CommentEntity> comment;

  @Builder(builderMethodName = "toCommunityBuilder", builderClassName = "toCommunityBuilder")
  public CommunityEntity(CommunityDTO communityDTO, UserDTO user) {
    this.communityId = communityDTO.getCommunityId();
    this.title = communityDTO.getTitle();
    this.content = communityDTO.getContent();
    this.category = communityDTO.getCategory();
    this.user = UserEntity.createUserBuilder().userDTO(user).build();
    this.comment = new ArrayList<>();
  }

  public void updateCommunity(CommunityDTO communityDTO) {
    this.title = communityDTO.getTitle();
    this.content = communityDTO.getContent();
  }
}
