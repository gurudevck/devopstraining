package com.hsbc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateStudentException.class)
    @ResponseStatus(reason ="duplicate student", code = HttpStatus.BAD_REQUEST)//500
    public void handleException(){
        //log the exception
    }
}
