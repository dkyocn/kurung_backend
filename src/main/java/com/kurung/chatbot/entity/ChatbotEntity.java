package com.kurung.chatbot.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_CHATBOT")
public class ChatbotEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CHATBOT_ID", nullable = false)
  private int chatbotId;

  @Column(name = "USER_UUID", nullable = false)
  private String userUuid;

  @Column(name = "QUESTION", nullable = false)
  private boolean question;

  @Column(name = "ANSWER", nullable = false)
  private boolean answer;

  @Column(name = "CONVERSATION_TIME", nullable = false)
  private Date conversationTime;
}
