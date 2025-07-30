package com.kurung.chatbot.dto;

import com.kurung.chatbot.entity.ChatbotEntity;
import com.kurung.user.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotDTO {

  @Schema(description = "챗봇 ID", example = "1")
  protected int chatbotId;

  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  protected UserDTO userDTO;

  @Schema(description = "질문 여부 (true = 질문, false = 응답)", example = "true")
  protected String  question;

  @Schema(description = "응답 여부 (true = 응답, false = 질문)", example = "true")
  protected String  answer;

  @Schema(description = "대화 시간", example = "2025-07-07 13:00:00")
  protected Date conversationTime;

  @Builder(builderMethodName = "toChatbotBuilder", builderClassName = "toChatbotBuilder")
  public ChatbotDTO(ChatbotEntity chatbotEntity) {
    this.chatbotId = chatbotEntity.getChatbotId();
    this.userDTO = chatbotEntity.getUser() != null ? UserDTO.toUserBuilder().userEntity(chatbotEntity.getUser()).build() : null;
    this.question = chatbotEntity.getQuestion();
    this.answer = chatbotEntity.getAnswer();
    this.conversationTime = chatbotEntity.getConversationTime();
  }
}
