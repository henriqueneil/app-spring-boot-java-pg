package com.henriqueneil.app.springboot.exceptions;

/**
 * This specialised exception class handles exception thrown by any validation
 * in the application in order to handle them in a consistent way.
 * <p>
 * The validation exceptions can be any of the type malformed request, invalid values or missing values
 *
 * @author Henrique de Oliveira
 */
public class ValidationException extends RuntimeException {

    public ValidationException(final String message) {
        super(message);
    }

    public ValidationException(final String message, Throwable cause) {
        super(message, cause);
    }
}
