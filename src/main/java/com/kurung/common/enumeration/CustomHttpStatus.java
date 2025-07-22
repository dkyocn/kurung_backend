package com.kurung.common.enumeration;

import lombok.Getter;

@Getter
public enum CustomHttpStatus {

  // 4XX client Error 418 ~ 421, 452 ~ 499
  // User
  USER_NOT_FOUND(418, "해당 id의 사용자가 없습니다."),

  //SNS Login
  SOCIAL_LOGIN_FAILED(419,"카카오 로그인 처리 중 오류가 발생했습니다."),
  DUPLICATE_EMAIL(420, "이미 해당 이메일로 가입된 계정이 있습니다."),

  // Stress
  STRESSRELIEF_NOT_FOUND(453, "해당 id의 사용자가 없습니다."),

  // Diagnosis

  // Diet
  DIET_NOT_FOUND(463, "해당 id의 식단이 없습니다."),
  SCORE_NOT_FOUND(464, "해당 id의 식단 점수가 없습니다."),
  FOOD_NOT_FOUND(465, "해당 id의 음식이 없습니다."),

  // HEALTH_INFO
  HEALTH_INFO_NOT_FOUND(466, "해당 id의 건강정보가 없습니다."),

  // Exercise
  EXERCISELOG_NOT_FOUND(468, "해당 id의 운동기록이 없습니다."),
  EXERCISE_NOT_FOUND(469, "해당 id의 운동의 정보가 없습니다."),
  OBJECTIVE_NOT_FOUND(470, "해당 id의 운동목표가 없습니다."),
  ROUTINE_NOT_FOUND(471, "해당 id의 운동루틴이 없습니다."),

  // LifeLog
  LIFELOG_NOT_FOUND(473,"해당 id의 라이프 로그가 없습니다."),
  MONTHLYLIFELOG_NOT_FOUND(474,"해당 id의 라이프 로그가 없습니다."),
  // HealthReport

  // Mission
  MISSIONS_NOT_FOUND(483, "해당 id의 미션이 없습니다."),

  // Medicine

  // Favorite
  FAVORITE_NOT_FOUND(491, "해당 id의 즐겨찾기가 없습니다."),

  // Chatbot
  CHATBOT_NOT_FOUND(494, "해당 id의 챗봇 대화가 없습니다."),

  // Community
  COMMUNITY_NOT_FOUND(497, "해당 id의 커뮤니티가 없습니다."),

  // 5XX Server Error 506, 512 ~ 599

  // Diagnosis

  // DIET
  DIET_SAVE_ERROR(526, "식단 저장을 실패하였습니다."),
  DIET_UPDATE_ERROR(527, "식단 수정을 실패하였습니다."),
  DIET_DELETE_ERROR(528,"식단 삭제를 실패하였습니다."),

  // EXERCISE
  EXERCISE_SAVE_ERROR(531, "운동기록 저장을 실패하였습니다."),
  VIDEO_SAVE_ERROR(533, "운동영상 저장을 실패하였습니다."),
  EXERCISE_UPDATE_ERROR(534, "운동기록 수정을 실패하였습니다."),
  EXERCISE_DELETE_ERROR(535,"운동기록 삭제를 실패하였습니다."),
  OBJECT_SAVE_ERROR(532, "운동목표 저장을 실패하였습니다."),
  OBJECT_UPDATE_ERROR(571, "운동목표 수정을 실패하였습니다."),
  OBJECT_DELETE_ERROR(572, "운동목표 삭제를 실패하였습니다."),

  // LifeLog
  LIFELOG_SAVE_ERROR(536,"라이프 로그 저장을 실패하였습니다."),
  LIFELOG_UPDATE_ERROR(537,"라이프 로그 수정을 실패하였습니다."),
  LIFELOG_DELETE_ERROR(538,"라이프 로그 삭제를 실패하였습니다."),

  // HealthReport

  //favorites
  FAVORITE_SAVE_ERROR(556,"즐겨찾기 등록을 실패하였습니다."),
  FAVORITES_DELETE_ERROR(557,"즐겨찾기 삭제를 실패하였습니다."),



  // Community
  COMMUNITY_SAVE_ERROR(566, "커뮤니티 보드 저장을 실패하였습니다."),
  COMMUNITY_UPDATE_ERROR(567,"커뮤니티 수정을 실패하였습니다."),
  COMMUNITY_DELETE_ERROR(568,"커뮤니티 삭제를 실패하였습니다."),

  // HEALTH_INFO
  HEALTH_INFO_UPDATE_ERROR(573," 건강정보 수정을 실패하였습니다."),
  HEALTH_INFO_SAVE_ERROR(574,"건강정보 생성을 실패하였습니다.");


  private final int value;
  private final String message;

  CustomHttpStatus(int value, String message) {
    this.value = value;
    this.message = message;
  }

}
