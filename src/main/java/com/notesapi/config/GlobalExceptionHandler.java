package com.notesapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", "Bad Request");
        
        // If it's an auth error, return 401, otherwise 400
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (ex.getMessage().contains("Invalid email or password") || ex.getMessage().contains("User not found")) {
            status = HttpStatus.UNAUTHORIZED;
            body.put("error", "Unauthorized");
        }

        return new ResponseEntity<>(body, status);
    }
}
