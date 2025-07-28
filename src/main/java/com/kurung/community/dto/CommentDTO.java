package com.kurung.community.dto;

import com.kurung.community.entity.CommentEntity;
import com.kurung.user.dto.UserDTO;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

  protected int commentId;
  protected String content;
  protected LocalDateTime createdAt;
  protected UserDTO user;

  @Builder(builderMethodName = "toCommentBuilder", builderClassName = "toCommentBuilder")
  public CommentDTO (CommentEntity commentEntity) {
    this.commentId = commentEntity.getCommentId();
    this.content = commentEntity.getContent();
    this.createdAt = commentEntity.getCreatedAt();
    this.user = commentEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(commentEntity.getUser()).build() : null;
  }
}
