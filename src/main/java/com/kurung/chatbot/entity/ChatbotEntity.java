package com.kurung.chatbot.entity;

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

  @Column(name = "QUESTION", nullable = false)
  private String  question;

  @Column(name = "ANSWER", nullable = false)
  private String  answer;

  @CreatedDate
  @Column(name = "CONVERSATION_TIME", nullable = false)
  private Date conversationTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_UUID", nullable = false)
  private UserEntity user;
}
