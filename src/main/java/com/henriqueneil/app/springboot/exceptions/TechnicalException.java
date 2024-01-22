package com.henriqueneil.app.springboot.exceptions;

import lombok.Getter;

/**
 * This specialised exception class handles any unexpected exception thrown by the application
 * during any execution in order to handle them in a consistent way by the exception handler engine.
 *
 * @author Henrique de Oliveira
 */
@Getter
public class TechnicalException extends RuntimeException {

    private Integer code;

    public TechnicalException() {
        super("A unexpected exception happened.");
    }

    public TechnicalException(final String message) {
        super(message);
    }

    public TechnicalException(final Integer code, final String message) {
        super(message);
        this.code = code;
    }

    public TechnicalException(final String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(final Integer code, final String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}

