package com.espark.adarsh.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {
    private final String correlationId;
    private final HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime datetime;
    private final String error;

    public ExceptionResponse(@NonNull HttpStatus status, @NonNull Exception ex, String correlationId) {
        datetime = LocalDateTime.now();
        this.status = status;
        this.error = ex.getMessage();
        this.correlationId = correlationId;
    }
}

