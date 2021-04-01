package com.su.accounting.exception;

import lombok.Data;

/**
 * Accounting Service Exception.
 */
@Data
public class ServiceException extends RuntimeException {
    private int statusCode;
    private String errorCode;
    private ServiceException.ErrorType errorType; // Service, Client, Unknown

    /**
     * the error type enum.
     */
    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public ServiceException(String message) {
        super(message);
    }
}
