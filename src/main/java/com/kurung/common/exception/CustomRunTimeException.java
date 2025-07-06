package com.kurung.common.exception;

import com.kurung.common.enumeration.CustomHttpStatus;
import lombok.Getter;

@Getter
public class CustomRunTimeException extends RuntimeException {
    private final int statusCode;

    public CustomRunTimeException(CustomHttpStatus customHttpStatus) {
        super(customHttpStatus.getMessage());
        this.statusCode = customHttpStatus.getValue();

    }

}
