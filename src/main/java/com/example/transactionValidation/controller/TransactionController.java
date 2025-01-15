package com.example.transactionValidation.controller;

import com.example.transactionValidation.exceptions.TransactionValidationException;
import com.example.transactionValidation.model.Transfer;
import com.example.transactionValidation.model.TransferRequest;
import com.example.transactionValidation.service.TransactionsService;
import com.example.transactionValidation.service.TransferService;
import com.example.transactionValidation.service.TransactionsService.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransferService transferService;

    public TransactionController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@RequestBody TransferRequest transferRequest) {

        try {
            Transfer transfer = transferService.transferFunds(
                    transferRequest.getSourceAccountId(),
                    transferRequest.getDestinationAccountId(),
                    transferRequest.getAmount(),
                    transferRequest.getCurrency(),
                    transferRequest.getTimestamp()

            );
            Double updatedSourceBalance = transferService.getSourceAccountBalance(transferRequest.getSourceAccountId());

            String successMessage =
                    String.format("Transfer is completed. %.2f %s transferred from account %d to account %d., The balance of the source account is %.2f ",
                    transferRequest.getAmount(), transferRequest.getCurrency(),
                    transferRequest.getSourceAccountId(), transferRequest.getDestinationAccountId(), updatedSourceBalance);
            return ResponseEntity.ok(new TransferResponse(successMessage, transfer));
        } catch (IllegalArgumentException e) {
            ValidationErrorResponse errorResponse = new TransactionsService.ValidationErrorResponse(e.getMessage(), List.of(e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    public static class TransferResponse {
        private String message;
        private Transfer transfer;

        public TransferResponse(String message, Transfer transfer) {
            this.message = message;
            this.transfer = transfer;
        }

        public String getMessage() {
            return message;
        }

        public Transfer getTransfer() {
            return transfer;
        }
    }
}
