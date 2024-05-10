package com.example.jobmarket.GlobalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle SQLIntegrityConstraintViolationException
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        // Log the exception (optional, but useful for debugging)
        System.err.println("SQLIntegrityConstraintViolationException: " + ex.getMessage());

        return new ResponseEntity<>("User with this email already exists", HttpStatus.CONFLICT);
    }

    // Define a simple ErrorResponse class to structure error messages

}
