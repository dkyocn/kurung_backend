package com.kurung.chatbot.entity;

import com.kurung.user.entity.UserEntity;
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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;

//  @Column(name = "USER_UUID", nullable = false)
//  private String userUuid;

  @Column(name = "QUESTION", nullable = false)
  private String  question;

  @Column(name = "ANSWER", nullable = false)
  private String  answer;

  @Column(name = "CONVERSATION_TIME", nullable = false)
  private Date conversationTime;

  @PrePersist
  protected void onCreate() {
    this.conversationTime = new Date();
  }
}
