package com.henriqueneil.app.springboot.exceptions;

import com.henriqueneil.app.springboot.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j(topic = "rest-exception-handler")
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * The handler when a validation exception occurs, a bad request.
     *
     * @param exception The exception that has been thrown.
     * @param request The request that generated that exception.
     * @return ResponseEntity The response entity with the information about the error.
     */
    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Error> handleBadRequest(RuntimeException exception, WebRequest request) {
        log.error("ValidationException", exception);
        return new ResponseEntity<>(Error.builder().code(400).message(exception.getMessage()).build(),
                getDefaultHeaders(), BAD_REQUEST);
    }

    /**
     * The handler for internal server error which is an unexpected error during
     * any execution in the application. All exceptions must be caught and handle
     * to a {@link TechnicalException} so that this interceptor can handle them.
     *
     * @param exception The exception that has been thrown.
     * @param request The request that generated the exception.
     * @return ResponseEntity The response entity with the information about the error.
     */
    @ExceptionHandler(value = {TechnicalException.class})
    public ResponseEntity<Error> handleInternalServerError(TechnicalException exception, WebRequest request) {
        log.error("TechnicalException", exception);
        return new ResponseEntity<>(Error.builder()
                .code(Optional.ofNullable(exception.getCode()).orElse(500))
                .message(exception.getMessage()).build(), getDefaultHeaders(), INTERNAL_SERVER_ERROR);
    }

    /**
     * This exception handler captures all exception that are not being handled in the code and are thrown
     * at runtime. Note that this handler will only be called if there is no other better match.
     *
     * @param exception The {@link RuntimeException} that has not been handled and was thrown at runtime.
     * @param request The request that caused the exception
     * @return ResponseEntity The response entity that holds the details of the response.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> handleException(RuntimeException exception, WebRequest request) {
        log.error("Unexpected exception not caught.", exception);
        var error = Error.builder().code(INTERNAL_SERVER_ERROR.value())
                .message(exception.getMessage()).build();
        return new ResponseEntity<>(error, getDefaultHeaders(), INTERNAL_SERVER_ERROR);
    }

    /**
     * Returns the default headers which sets:
     *      - Content Type: application/json
     *
     * @return HttpHeaders The HTTP Headers to be returned.
     */
    private HttpHeaders getDefaultHeaders () {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}

