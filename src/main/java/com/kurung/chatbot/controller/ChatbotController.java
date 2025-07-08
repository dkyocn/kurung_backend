package com.kurung.chatbot.controller;

import com.kurung.chatbot.dto.ChatbotDTO;
import com.kurung.chatbot.service.ChatbotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kurung/chatbot")
@Tag(name = "CHATBOT API TEST", description = "챗봇 API 테스트용입니다.")
public class ChatbotController {

  private final ChatbotService chatbotService;

  @GetMapping("/{id}")
  @Operation(summary = "챗봇 대화 단일 조회", description = "하나의 챗봇 대화를 ID로 조회하는 API")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(mediaType = "application/json")),
      @ApiResponse(responseCode = "418", description = "조회 실패", content = @Content(mediaType = "application/json"))
  })
  @Parameter(name = "id", description = "챗봇 대화 ID", example = "1")
  public ResponseEntity<List<ChatbotDTO>> getChatbotById(@PathVariable int id) {
    return new ResponseEntity<>(chatbotService.getChatbotById(id), HttpStatus.OK);
  }
}
