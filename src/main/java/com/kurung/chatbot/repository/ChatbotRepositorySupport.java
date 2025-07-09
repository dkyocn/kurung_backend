package com.kurung.chatbot.repository;

import com.kurung.chatbot.entity.ChatbotEntity;
import java.util.List;

public interface ChatbotRepositorySupport {

  List<ChatbotEntity> getChatbotById(int id);

  ChatbotEntity getLatestChatbot(String uuid);
}
