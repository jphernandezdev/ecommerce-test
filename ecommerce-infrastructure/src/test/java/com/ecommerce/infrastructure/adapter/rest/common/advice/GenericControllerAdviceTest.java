package com.ecommerce.infrastructure.adapter.rest.common.advice;

import com.ecommerce.api.model.ErrorResponseDto;
import com.ecommerce.domain.common.model.ErrorCatalog;
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

class GenericControllerAdviceTest {

    private GenericControllerAdvice advice;

    @BeforeEach
    void setUp() {
        advice = new GenericControllerAdvice();
    }

    @Test
    void whenMissingServletRequestParameterException_thenReturnsBadRequest() {
        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("param", "String");
        ResponseEntity<ErrorResponseDto> response = advice.handleMissingServletRequestParameterException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.INVALID_PARAMETERS.getCode(), response.getBody().getCode());
        assertEquals(ErrorCatalog.INVALID_PARAMETERS.getMessage(), response.getBody().getMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains(ex.getMessage()));
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void whenMethodArgumentTypeMismatchException_thenReturnsBadRequest() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getMessage()).thenReturn("Type mismatch");

        ResponseEntity<ErrorResponseDto> response = advice.handleMethodArgumentTypeMismatchException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.INVALID_PARAMETERS.getCode(), response.getBody().getCode());
        assertEquals(ErrorCatalog.INVALID_PARAMETERS.getMessage(), response.getBody().getMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains("Type mismatch"));
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    void whenGenericException_thenReturnsInternalServerError() {
        Exception ex = new Exception("Generic error");
        ResponseEntity<ErrorResponseDto> response = advice.handleGenericError(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ErrorCatalog.GENERIC_ERROR.getCode(), response.getBody().getCode());
        assertEquals(ErrorCatalog.GENERIC_ERROR.getMessage(), response.getBody().getMessage());
        assertTrue(Objects.requireNonNull(response.getBody().getDetails()).contains("Generic error"));
        assertNotNull(response.getBody().getTimestamp());
    }
}
