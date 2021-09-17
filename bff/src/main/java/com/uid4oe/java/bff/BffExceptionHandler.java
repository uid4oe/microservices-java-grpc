package com.uid4oe.java.bff;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class BffExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(BffResponse.builder().error(e.getMessage()).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}