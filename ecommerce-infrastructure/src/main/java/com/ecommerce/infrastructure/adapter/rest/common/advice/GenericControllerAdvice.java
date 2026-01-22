package com.ecommerce.infrastructure.adapter.rest.common.advice;

import com.ecommerce.domain.common.model.ErrorCatalog;
import com.ecommerce.infrastructure.adapter.rest.common.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
public class GenericControllerAdvice {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCatalog.INVALID_PARAMETERS, exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCatalog.INVALID_PARAMETERS, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericError(Exception exception) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.GENERIC_ERROR, exception);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, ErrorCatalog error, Exception exception) {
        ErrorResponse response = ErrorResponse.builder()
                .code(error.getCode())
                .message(error.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, status);
    }
}
