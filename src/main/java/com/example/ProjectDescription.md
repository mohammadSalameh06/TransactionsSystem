#  **AccountFlow **

##  **Purpose**

This project provides a system for validating and processing financial transactions between accounts. 
It ensures that funds are transferred only if the source account has sufficient balance, 
while also validating transaction details like the account IDs, amount, currency, 
timestamp, and account status (ensuring the account is active to complete the transaction). 
Additionally, it includes CRUD operations for managing accounts effectively.
##  **Key Features**
- **Transaction Validation**: Verifies if the transaction details are valid (e.g., correct currency, valid account IDs, sufficient funds).
- **Fund Transfer**: Processes the transfer of funds between two accounts after validating the transaction.
- **Error Handling**: Gracefully handles errors such as insufficient funds, invalid account IDs, or unsupported currencies.
- **Transaction Logging**: Records every transfer made with the timestamp, source account, destination account, amount, and currency.
- **Custom Success & Error Messages**: Returns descriptive responses for both successful and failed transactions.

##  **Structure**
- **`TransferService`**: Handles the main business logic for transferring funds between accounts, including validation and updating account balances.
- **`TransactionsService`**: Validates the individual transaction details such as account existence, amount, and timestamp.
- **`TransactionController`**: Exposes an API endpoint (`/api/transactions/transfer`) to accept transaction requests and interact with the services.
- **`TransferRequest`**: A data transfer object that holds the request details such as source account, destination account, amount, currency, and timestamp.
- **`Transfer`**: A model that represents a completed transfer, storing the relevant transaction data.
- **`TransactionValidationException`**: Custom exception for handling validation errors in transaction processing.

##  **Dependencies**
- **Spring Boot**: A framework used for building the backend API.
- **Jakarta Transaction**: Ensures atomic transactions and rollback functionality.
- **Spring Data JPA**: Used for interacting with the database (e.g., `AccountRepo` and `TransferRepository`).
- **Java Time API**: Handles date and time formatting for transaction timestamps.

## ⚙ **How It Works**
1. A user sends a transaction request to the `/api/transactions/transfer` endpoint.
2. The system validates the request data (account IDs, amount, currency, timestamp).
3. If valid, the transfer occurs by updating the account balances in the database.
4. If there’s an error (e.g., insufficient funds or invalid data), a descriptive error message is returned.
5. The transfer is recorded in the `TransferRepository`, storing the details of the completed transaction.

##  **Example Usage**
### Create Account
```json1
POST /api/accounts
{
"accountId": 1,
"accountHolderName": "John Doe",
"balance": 1000.00,
"accountStatus": "ACTIVE",
"accountType": "CURRENT"
}
```
### Get Account by ID
```json1
GET /accounts/{accountId}
{
"accountId": 1,
"accountHolderName": "John Doe",
"balance": 1000.00,
"accountStatus": "ACTIVE",
"accountType": "CURRENT",
}
```
### Update Account
```json1
PUT /accounts/{accountId}
{
"accountHolderName": "John Doe Updated",
"balance": 1500.00,
"accountStatus": "ACTIVE",
"accountType": "SAVINGS"
}

```
### Request
```json1
POST /api/transactions/transfer
{
  "sourceAccountId": 1,
  "destinationAccountId": 2,
  "amount": 100.00,
  "currency": "USD",
  "timestamp": "2025-01-12T10:30:00"
}
