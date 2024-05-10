package com.espark.adarsh.exception.handler;

import com.espark.adarsh.exception.ExceptionResponse;
import com.espark.adarsh.log.CorrelationIDHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    private final CorrelationIDHandler correlationIDHandler;

    public ApplicationExceptionHandler(CorrelationIDHandler correlationIDHandler) {
        this.correlationIDHandler = correlationIDHandler;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        log.warn(exception.getMessage(), exception);
        var exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, exception, correlationIDHandler.getCorrelationId());
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }
}

