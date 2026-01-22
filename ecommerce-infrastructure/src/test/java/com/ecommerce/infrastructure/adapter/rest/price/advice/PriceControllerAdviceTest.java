package com.ecommerce.infrastructure.adapter.rest.price.advice;

import com.ecommerce.api.model.ErrorResponseDto;
import com.ecommerce.domain.common.model.ErrorCatalog;
import com.ecommerce.domain.price.exception.PriceInquiryInvalidParameterException;
import com.ecommerce.domain.price.exception.PriceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceControllerAdviceTest {

    private PriceControllerAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new PriceControllerAdvice();
    }

    @Test
    void whenPriceNotFoundException_thenReturnsNotFound() {
        PriceNotFoundException ex = new PriceNotFoundException("Price not found");
        ResponseEntity<ErrorResponseDto> response = advice.handlePriceNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.PRICE_NOT_FOUND.getCode(), response.getBody().getCode());
        assertEquals(ErrorCatalog.PRICE_NOT_FOUND.getMessage(), response.getBody().getMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains("Price not found"));
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void whenPriceInquiryInvalidParameterException_thenReturnsBadRequest() {
        PriceInquiryInvalidParameterException ex = new PriceInquiryInvalidParameterException("Invalid param");
        ResponseEntity<ErrorResponseDto> response = advice.handlePriceInvalidParameterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getCode(), response.getBody().getCode());
        assertEquals(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getMessage(), response.getBody().getMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains("Invalid param"));
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void whenMissingServletRequestParameterException_thenReturnsBadRequest() {
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("param", "String");
        ResponseEntity<ErrorResponseDto> response = advice.handleMissingServletRequestParameterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getCode(), response.getBody().getCode());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains(ex.getMessage()));
    }

    @Test
    void whenMethodArgumentTypeMismatchException_thenReturnsBadRequest() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getMessage()).thenReturn("Type mismatch");
        
        ResponseEntity<ErrorResponseDto> response = advice.handleMethodArgumentTypeMismatchException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.PRICE_INQUIRY_INVALID_PARAMETERS.getCode(), response.getBody().getCode());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains("Type mismatch"));
    }
}
