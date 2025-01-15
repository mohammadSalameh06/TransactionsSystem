package com.example.transactionValidation.exceptions;

import com.example.transactionValidation.model.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionValidationException.class)
    public ResponseEntity<ValidationResponse> handleTransactionValidationException(TransactionValidationException ex) {
        ValidationResponse response = new ValidationResponse(null, false, ex.getErrors().toString());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationResponse> handleGlobalException(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add("Unexpected error: " + ex.getMessage());
        ValidationResponse response = new ValidationResponse(null, false, errors.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
