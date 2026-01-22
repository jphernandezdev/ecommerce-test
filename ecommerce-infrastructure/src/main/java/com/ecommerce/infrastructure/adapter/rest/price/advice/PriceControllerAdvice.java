package com.ecommerce.infrastructure.adapter.rest.price.advice;

import com.ecommerce.api.model.ErrorResponseDto;
import com.ecommerce.domain.common.model.ErrorCatalog;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.price.exception.PriceNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Collections;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PriceControllerAdvice {

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handlePriceNotFoundException(PriceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto()
                .code(ErrorCatalog.PRICE_NOT_FOUND.getCode())
                .message(ErrorCatalog.PRICE_NOT_FOUND.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now()));
    }

    @ExceptionHandler(PriceInquiryInvalidParameterException.class)
    public ResponseEntity<ErrorResponseDto> handlePriceInvalidParameterException(PriceInquiryInvalidParameterException exception) {
        return buildBadRequestResponse(exception);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDto> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return buildBadRequestResponse(exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return buildBadRequestResponse(exception);
    }

    private ResponseEntity<ErrorResponseDto> buildBadRequestResponse(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDto()
                .code(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getCode())
                .message(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getMessage())
                .details(Collections.singletonList(exception.getMessage()))
                .timestamp(LocalDateTime.now()));
    }

}
