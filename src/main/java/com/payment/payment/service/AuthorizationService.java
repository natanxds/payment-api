package com.payment.payment.service;

import com.payment.payment.client.AuthorizationClient;
import com.payment.payment.dto.TransferDto;
import com.payment.payment.entity.Transfer;
import com.payment.payment.exception.PaymentException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transferDto) {
        var response = authorizationClient.isAuthorized();
        if (response.getStatusCode().isError()) {
            throw new PaymentException();
        }

        return response.getBody().authorized();
    }
}
