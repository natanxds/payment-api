package com.payment.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PaymentException extends RuntimeException{
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Payment Internal Server Error");

        return pb;
    }
}
