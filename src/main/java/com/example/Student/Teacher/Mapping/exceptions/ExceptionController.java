package com.example.Student.Teacher.Mapping.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),e.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception e, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),e.getMessage(),request.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);




    }
}
