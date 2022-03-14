package com.infinitiessoft.test.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(RuntimeException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("IllegalArgumentException：" + exception.getMessage());
    }
}
