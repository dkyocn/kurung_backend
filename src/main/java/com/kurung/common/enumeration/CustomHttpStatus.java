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

  // User

  // Stress

  // Diagnosis

  // DIET
  DIET_SAVE_ERROR(526, "식단 저장을 실패하였습니다."),
  DIET_UPDATE_ERROR(527, "식단 수정을 실패하였습니다."),
  DIET_DELETE_ERROR(528,"식단 삭제를 실패하였습니다."),

  // Exercise

  // LifeLog
  LIFELOG_SAVE_ERROR(536,"라이프 로그 저장을 실패하였습니다.");

  // HealthReport

  // Mission

  // Medicine

  // Favorite

  // Chatbot

  // Community


  private final int value;
  private final String message;

  CustomHttpStatus(int value, String message) {
    this.value = value;
    this.message = message;
  }
  }
