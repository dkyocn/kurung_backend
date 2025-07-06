package com.kurung.common.enumeration;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomHttpStatus {

    // 4XX client Error 418 ~ 421, 452 ~ 499
    DIET_NOT_FOUND(418, "해당 id의 식단이 없습니다.");
    // 5XX Server Error 506, 512 ~ 599

    private final int value;
    private final String message;

    CustomHttpStatus(int value, String message) {
        this.value = value;
        this.message = message;
    }
}
