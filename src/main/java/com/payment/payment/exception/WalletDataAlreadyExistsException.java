package com.payment.payment.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@AllArgsConstructor
public class WalletDataAlreadyExistsException extends PaymentException {

    private String detail;

    @Override
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Wallet Data Already Exists");
        pb.setDetail(detail);
        return pb;
    }
}
