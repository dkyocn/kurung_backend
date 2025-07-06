package com.kurung.common.exception;

import com.kurung.common.enumeration.CustomHttpStatus;
import com.kurung.diet.controller.DietController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice(annotations = {RestController.class},basePackageClasses = {DietController.class})
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomRunTimeException.class)
    public ResponseEntity<ProblemDetail> handleRuntimeCustomException(CustomRunTimeException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.valueOf(ex.getStatusCode()),
                ex.getMessage()
        );
        problemDetail.setTitle("Runtime exception");
        return new ResponseEntity<>(problemDetail, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(CustomIllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentCustomException(CustomIllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getStatusCode(),
                ex.getMessage(),
                getPath(request)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
    }

    // 요청 경로를 추출하는 유틸리티 메서드
    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
