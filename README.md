# Payment API

This API is a payment service that allows users to create wallets, deposit and withdraw funds, and transfer funds between wallets. It's built using Java, Spring Boot, Maven and MySQL.

## Technologies Used

- **Java**
- **Spring Boot**
- **Java**
- **MySQL**

## Endpoints

### Wallet Endpoints

- `POST /api/v1/wallets`: Create a new wallet. The request body should be a JSON object with the following properties:
  - `fullname`: The full name of the wallet owner.
  - `cpf_cnpj`: The CPF or CNPJ of the wallet owner.
  - `email`: The email of the wallet owner.
  - `password`: The password for the wallet.
  - `walletType`: The type of the wallet (either "user" or "merchant").

- `POST /api/v1/wallets/deposit`: Deposit an amount into a wallet. The request body should be a JSON object with the following properties:
  - `walletKey`: The CPF, CNPJ, or email associated with the wallet.
  - `amount`: The amount to deposit.

- `POST /api/v1/wallets/withdraw`: Withdraw an amount from a wallet. The request body should be a JSON object with the following properties:
  - `walletKey`: The CPF, CNPJ, or email associated with the wallet.
  - `amount`: The amount to withdraw.

- `GET /api/v1/wallets/transactions`: Get a statement of transactions for a wallet. The request body should be a JSON object with the following properties:
  - `walletKey`: The CPF, CNPJ, or email associated with the wallet.

### Transfer Endpoints

- `POST /api/v1/transfers`: Transfer an amount from one wallet to another. The request body should be a JSON object with the following properties:
  - `payer`: The CPF, CNPJ, or email associated with the payer's wallet.
  - `payee`: The CPF, CNPJ, or email associated with the payee's wallet.
  - `value`: The amount to transfer.

## Running the Application

To run the application, you need to have Java and Maven installed on your machine. You can then clone the repository and run the application using the following command:

```bash
mvn spring-boot:run
```

This will start the application on port 8080. You can then access the API endpoints at `http://localhost:8080/api/v1/`.

## Testing

To run the tests for the application, you can use the following command:

```bash
mvn test
```

This will run all the unit tests and integration tests for the application.

## Credits

- [Build & Run](https://www.youtube.com/watch?v=dttXo48oXt4&ab_channel=Build%26Run)

