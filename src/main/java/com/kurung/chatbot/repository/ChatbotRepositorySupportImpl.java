package com.kurung.chatbot.repository;

import static com.kurung.chatbot.entity.QChatbotEntity.chatbotEntity;

import com.kurung.chatbot.entity.ChatbotEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatbotRepositorySupportImpl implements ChatbotRepositorySupport {

  private final JPAQueryFactory jpaQueryFactory;

//  @Override
//  public List<ChatbotEntity> getChatbotById(int id) {
//    return List.of();
//  }

//  public List<ChatbotEntity> getChatbotByUser(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(ChatbotEntity)
//        .where(ChatbotEntity.userUuid.eq(uuid))
//        .fetch();
//  }
//
//  public ChatbotEntity getLatestChatbot(String uuid) {
//    return jpaQueryFactory
//        .selectFrom(chatbotEntity)
//        .where(chatbotEntity.userUuid.eq(uuid))
//        .orderBy(chatbotEntity.conversationTime.desc())
//        .limit(1)
//        .fetchOne();
//  }
//
//  @Override
//  public List<ChatbotEntity> getChatbotById(int id) {


  public List<ChatbotEntity> getChatbotById(int id) {
    return jpaQueryFactory
        .selectFrom(chatbotEntity)
        .where(chatbotEntity.chatbotId.eq(id))
        .fetch();
  }


  public List<ChatbotEntity> getChatbotByUser(String uuid) {
    return jpaQueryFactory
        .selectFrom(chatbotEntity)
//        .where(chatbotEntity.userUuid.eq(uuid))
        .where(chatbotEntity.user.userUuid.eq(uuid))
        .fetch();
  }

  @Override
  public ChatbotEntity getLatestChatbot(String uuid) {
    return jpaQueryFactory
        .selectFrom(chatbotEntity)
//        .where(chatbotEntity.userUuid.eq(uuid))
        .where(chatbotEntity.user.userUuid.eq(uuid))
        .orderBy(chatbotEntity.conversationTime.desc())
        .limit(1)
        .fetchOne();
  }
}
