package com.kurung.common.enumeration;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomHttpStatus {

    // 4XX client Error 418 ~ 421, 452 ~ 499
    DIET_NOT_FOUND(418, "해당 id의 식단이 없습니다."),
    FAVORITE_NOT_FOUND(419, "해당 id의 즐겨찾기가 없습니다."),
    CHATBOT_NOT_FOUND(420, "해당 id의 챗봇 대화가 없습니다.");

    // 5XX Server Error 506, 512 ~ 599

    private final int value;
    private final String message;

    CustomHttpStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
