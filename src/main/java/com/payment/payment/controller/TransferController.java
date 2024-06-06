package com.payment.payment.controller;

import com.payment.payment.dto.TransferDto;
import com.payment.payment.entity.Transfer;
import com.payment.payment.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto transferDto) {
        var response = transferService.transfer(transferDto);
        return ResponseEntity.ok(response);
    }
}
