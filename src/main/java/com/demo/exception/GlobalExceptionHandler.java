package com.demo.exception;

import com.demo.Dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception e, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
                e.getMessage(),               // Corrected getmsg() to getMessage()
                new Date(),                   // Corrected "new date()" to "new Date()"
                request.getDescription(false) // Corrected typo and added parameter
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFound e, WebRequest request) {
        ErrorDetails error = new ErrorDetails(
                e.getMessage(),               // Corrected getmsg() to getMessage()
                new Date(),                   // Corrected "new date()" to "new Date()"
                request.getDescription(false) // Corrected typo and added parameter
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
