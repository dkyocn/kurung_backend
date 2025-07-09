package com.kurung.community.dto;

import com.kurung.common.dto.BaseDTO;
import com.kurung.common.enumeration.HealthType;
import com.kurung.community.entity.CommunityEntity;
import com.kurung.user.dto.UserDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDTO extends BaseDTO {

  protected int communityId;
  protected String title;
  protected String content;
  protected HealthType category;
  protected UserDTO user;
  protected List<CommentDTO> comment;

  @Builder(builderMethodName = "toCommunityBuilder", builderClassName = "toCommunityBuilder")
  public CommunityDTO(CommunityEntity communityEntity) {
    this.communityId = communityEntity.getCommunityId();
    this.title = communityEntity.getTitle();
    this.content = communityEntity.getContent();
    this.category = communityEntity.getCategory();
    this.user = communityEntity.getUser() != null ? UserDTO.toUserBuilder()
        .userEntity(communityEntity.getUser()).build() : null;
    this.comment = communityEntity.getComment() != null ? communityEntity.getComment().stream()
        .map(commentEntity -> CommentDTO.toCommentBuilder().commentEntity(commentEntity).build())
        .collect(
            Collectors.toList()) : null;

  }
}
