package com.example.transactionValidation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FrontController {

    @RequestMapping("/accounts")
    public ResponseEntity<?> handleAccountRequest(){
        return ResponseEntity.ok("Redirecting to AccountController");
    }
    @RequestMapping("/transactions")
    public ResponseEntity<?> handleTransactionRequest(){
        return ResponseEntity.ok("Redirecting to TransactionsController");
    }
    @RequestMapping("/*")
    public ResponseEntity<?> handleErrors(){
        return ResponseEntity.ok("The System can't found the Endpoint, check your URL");
    }

}
/**
 * The Front Controller is a centralized entry point for handling all incoming requests,
 * acting as the top-level controller. It delegates valid requests to appropriate sub-controllers
 * and returns an error response for invalid or unsupported endpoints.
 */