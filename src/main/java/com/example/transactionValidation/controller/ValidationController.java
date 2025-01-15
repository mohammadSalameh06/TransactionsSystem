package com.example.transactionValidation.controller;

import com.example.transactionValidation.model.Transaction;
import com.example.transactionValidation.model.ValidationResponse;
import com.example.transactionValidation.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class ValidationController {

    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/validate")
    public ResponseEntity<ValidationResponse> transactionValidation(@RequestBody Transaction transaction) {
        ValidationResponse response = transactionsService.validate(transaction);
        return ResponseEntity.ok(response);
    }
}
