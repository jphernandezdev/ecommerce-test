package com.ecommerce.infrastructure.adapter.rest.common.advice;

import com.ecommerce.api.model.ErrorResponseDto;
import com.ecommerce.domain.common.model.ErrorCatalog;
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
    public ResponseEntity<ErrorResponseDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCatalog.INVALID_PARAMETERS, exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ErrorCatalog.INVALID_PARAMETERS, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericError(Exception exception) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCatalog.GENERIC_ERROR, exception);
    }

    private ResponseEntity<ErrorResponseDto> buildErrorResponse(HttpStatus status, ErrorCatalog error, Exception exception) {
        ErrorResponseDto response = new ErrorResponseDto()
                .code(error.getCode())
                .message(error.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now());
        return new ResponseEntity<>(response, status);
    }
}
