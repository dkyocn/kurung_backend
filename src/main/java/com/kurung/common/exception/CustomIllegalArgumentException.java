package com.kurung.common.exception;

import com.kurung.common.enumeration.CustomHttpStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomIllegalArgumentException extends IllegalArgumentException {

  private final int statusCode;

  public CustomIllegalArgumentException(CustomHttpStatus customHttpStatus) {
    super(customHttpStatus.getMessage());
    this.statusCode = customHttpStatus.getValue();

  }
}
