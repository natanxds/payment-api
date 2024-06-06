package com.payment.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PaymentException{
    @Override
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer Not Authorized");

        return pb;
    }
}
