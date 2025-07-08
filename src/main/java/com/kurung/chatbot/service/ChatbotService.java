package com.kurung.chatbot.service;

import com.kurung.chatbot.dto.ChatbotDTO;
import java.util.List;

public interface ChatbotService {

  List<ChatbotDTO> getChatbotById(int chatbotId);
}
