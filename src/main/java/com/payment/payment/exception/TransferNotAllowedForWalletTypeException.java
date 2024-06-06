package com.payment.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalletTypeException extends PaymentException {
    @Override
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("This wallet type is not allowed to transfer money");

        return pb;
    }
}
