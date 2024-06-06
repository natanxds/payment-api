package com.payment.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends PaymentException {
    private BigDecimal amount;

    public InsufficientBalanceException(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Insufficient Balance");
        pb.setDetail("The wallet does not have enough balance to transfer $" + amount.toString());

        return pb;
    }
}
