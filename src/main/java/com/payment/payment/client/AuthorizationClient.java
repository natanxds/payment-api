package com.payment.payment.client;

import com.payment.payment.client.dto.AuthorizationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        url = "${client.authorization-service.url}",
        name = "authorization")
public interface AuthorizationClient {

    @GetMapping
    ResponseEntity<AuthorizationResponseDto> isAuthorized();

}
