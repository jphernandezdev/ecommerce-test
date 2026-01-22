package com.ecommerce.infrastructure.exception;

public class ApplicationTransactionException extends RuntimeException {
    public ApplicationTransactionException(Throwable cause) {
        super(cause);
    }

    public ApplicationTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
