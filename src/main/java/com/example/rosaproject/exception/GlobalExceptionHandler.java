package com.example.rosaproject.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String DEFULAUT_ERROR_VIEW = "error";


    @ExceptionHandler(value = NoSuchElementException.class)
    public String notFoundErrorHandler(Model model){
        return DEFULAUT_ERROR_VIEW;
    }


}
