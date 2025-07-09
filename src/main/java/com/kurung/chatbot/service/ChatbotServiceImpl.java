package com.kurung.chatbot.service;

import com.kurung.chatbot.dto.ChatbotDTO;
import com.kurung.chatbot.entity.ChatbotEntity;
import com.kurung.chatbot.repository.ChatbotRepository;
import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.common.exception.CustomIllegalArgumentException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

  private final ChatbotRepository chatbotRepository;

  @Override
  public List<ChatbotDTO> getChatbotById(int id) {
    List<ChatbotEntity> chatbotById = chatbotRepository.getChatbotById(id);

    if (chatbotById.isEmpty()) {
      throw new CustomIllegalArgumentException(CustomHttpStatus.CHATBOT_NOT_FOUND);
    }

    return chatbotById.stream().map(chatbotEntity -> ChatbotDTO.toChatbotBuilder().chatbotEntity(chatbotEntity).build()).collect(Collectors.toList());
  }

}
