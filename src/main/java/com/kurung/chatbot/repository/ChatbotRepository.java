package com.kurung.chatbot.repository;

import com.kurung.chatbot.entity.ChatbotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatbotRepository extends JpaRepository<ChatbotEntity, Integer>, ChatbotRepositorySupport {

}
