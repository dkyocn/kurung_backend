package com.kurung.common.enumeration;

import lombok.Getter;
import org.springframework.http.HttpStatus;

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

    // Exercise


    // LifeLog


    // HealthReport


    // Mission


    // Medicine


    // Favorite
    FAVORITE_NOT_FOUND(491, "해당 id의 즐겨찾기가 없습니다."),


    // Chatbot
    CHATBOT_NOT_FOUND(494, "해당 id의 챗봇 대화가 없습니다."),

   // Community
    COMMUNITY_NOT_FOUND(497, "해당 id의 커뮤니티가 없습니다.");


    // 5XX Server Error 506, 512 ~ 599

    private final int value;
    private final String message;

    CustomHttpStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
