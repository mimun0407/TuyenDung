package com.example.tuyendung1.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<String> runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.status(500).body(e.getMessage());
    }
}
