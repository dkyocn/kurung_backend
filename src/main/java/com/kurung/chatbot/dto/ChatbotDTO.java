package com.kurung.chatbot.dto;

import com.kurung.chatbot.entity.ChatbotEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ChatbotDTO {

  @Schema(description = "챗봇 ID", example = "1")
  private int chatbotId;

  @Schema(description = "사용자 UUID", example = "user-uuid-1234")
  private String userUuid;

  @Schema(description = "질문 여부 (true = 질문, false = 응답)", example = "true")
  private boolean question;

  @Schema(description = "응답 여부 (true = 응답, false = 질문)", example = "true")
  private boolean answer;

  @Schema(description = "대화 시간", example = "2025-07-07 13:00:00")
  private Date conversationTime;

  @Builder(builderMethodName = "toChatbotBuilder", builderClassName = "toChatbotBuilder")
  public ChatbotDTO(ChatbotEntity chatbotEntity) {
    this.chatbotId = chatbotEntity.getChatbotId();
    this.userUuid = chatbotEntity.getUserUuid();
    this.question = chatbotEntity.isQuestion();
    this.answer = chatbotEntity.isAnswer();
    this.conversationTime = chatbotEntity.getConversationTime();
  }
}
