package com.payment.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletNotFoundException extends PaymentException {

    private String walletId;

    public WalletNotFoundException(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public ProblemDetail toProblemDeatail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Wallet Not Found");
        pb.setDetail("There is no wallet with key: " + walletId);
        return pb;
    }
}
