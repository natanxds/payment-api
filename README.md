# Payment API

This API is a payment service that allows users to create wallets, deposit and withdraw funds, and transfer funds between wallets. It's built using Java, Spring Boot, Maven, and Docker.

## Technologies Used

- **Java**
- **Spring Boot**
- **Maven**
- **Docker**

## Endpoints

### Wallet Endpoints

- **Create a new wallet**

  `POST /api/v1/wallets`

  ```json
  {
    "fullname": "John Doe",
    "cpf_cnpj": "12345678901",
    "email": "john.doe@example.com",
    "password": "password123",
    "walletType": "user"
  }
  ```

- **Deposit an amount into a wallet**

  `POST /api/v1/wallets/deposit`

  ```json
  {
    "walletKey": "john.doe@example.com",
    "amount": 100.00
  }
  ```

- **Withdraw an amount from a wallet**

  `POST /api/v1/wallets/withdraw`

  ```json
  {
    "walletKey": "john.doe@example.com",
    "amount": 50.00
  }
  ```

- **Get a statement of transactions for a wallet**

  `GET /api/v1/wallets/transactions`

  ```json
  {
    "walletKey": "john.doe@example.com"
  }
  ```

### Transfer Endpoints

- **Transfer an amount from one wallet to another**

  `POST /api/v1/transfers`

  ```json
  {
    "payer": "john.doe@example.com",
    "payee": "jane.doe@example.com",
    "value": 25.00
  }
  ```

## Running the Application

To run the application mysql database, you need to have Docker installed on your machine. You can then clone the repository and navigate to the `docker` directory:

```bash
cd docker
```

Then, run the mysql database using the following command:

```bash
docker-compose up
```

This will start the MySQL database in a Docker container. 

To run the application, you need to have Java and Maven installed on your machine. You can then clone the repository and run the application using the following command:

```bash
mvn spring-boot:run
```

You can then access the API endpoints at `http://localhost:8080/api/v1/`.

## Testing

To run the tests for the application, you can use the following command:

```bash
mvn test
```

This will run all the unit tests and integration tests for the application.

## Contributing

If you would like to contribute to this project, please feel free to fork the repository, make your changes, and then submit a pull request. We appreciate any contributions that can help improve the project.
## Credits

- [Build & Run](https://www.youtube.com/watch?v=dttXo48oXt4&ab_channel=Build%26Run)

