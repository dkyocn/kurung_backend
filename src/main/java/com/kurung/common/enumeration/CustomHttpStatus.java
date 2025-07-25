package com.kurung.common.enumeration;

import lombok.Getter;

@Getter
public enum CustomHttpStatus {

  // 4XX client Error 418 ~ 421, 452 ~ 499
  // User
  USER_NOT_FOUND(418, "해당 id의 사용자가 없습니다."),

  // Stress
  STRESSRELIEF_NOT_FOUND(453, "해당 id의 사용자가 없습니다."),

  // Diagnosis
  QUESTION_NOT_FOUND(458, "건강진단 질문 리스트가 없습니다."),
  DIAGNOSIS_RESULT_NOT_FOUND(459, "해당 사용자의 건강진단 결과가 없습니다."),

  // Diet
  DIET_NOT_FOUND(463, "해당 id의 식단이 없습니다."),
  SCORE_NOT_FOUND(464, "해당 id의 식단 점수가 없습니다."),
  FOOD_NOT_FOUND(465, "해당 id의 음식이 없습니다."),

  // Exercise

  // LifeLog
    LIFELOG_NOT_FOUND(473,"해당 id의 라이프 로그가 없습니다."),
  
  // HealthReport

  // Mission

  // Medicine

  // Favorite
  FAVORITE_NOT_FOUND(491, "해당 id의 즐겨찾기가 없습니다."),


  // Chatbot
  CHATBOT_NOT_FOUND(494, "해당 id의 챗봇 대화가 없습니다."),

  // Community
  COMMUNITY_NOT_FOUND(497, "해당 id의 커뮤니티가 없습니다."),

  // 5XX Server Error 506, 512 ~ 599

  // Diagnosis
  ANSWER_SAVE_ERROR(521, "응답 저장을 실패하였습니다."),
  ANSWER_DELETE_ERROR(522, "응답 삭제를 실패하였습니다."),
  ANSWER_NULL_ERROR(523, "응답에 NULL값이 들어올 수 없습니다."),

  // DIET
  DIET_SAVE_ERROR(526, "식단 저장을 실패하였습니다."),
  DIET_UPDATE_ERROR(527, "식단 수정을 실패하였습니다."),
  DIET_DELETE_ERROR(528,"식단 삭제를 실패하였습니다.");
  private final int value;
  private final String message;

  CustomHttpStatus(int value, String message) {
    this.value = value;
    this.message = message;
  }
  }
