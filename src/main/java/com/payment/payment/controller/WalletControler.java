package com.payment.payment.controller;

import com.payment.payment.dto.CreateWalletDto;
import com.payment.payment.dto.WalletTransactionDto;
import com.payment.payment.dto.WalleyKeyDto;
import com.payment.payment.entity.PaymentTransaction;
import com.payment.payment.entity.Wallet;
import com.payment.payment.service.WalletService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletControler {

    private final WalletService walletService;

    public WalletControler(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto createWalletDto) {
        var wallet = walletService.createWallet(createWalletDto);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/deposit")
    public ResponseEntity<Wallet> deposit(@RequestBody @Valid WalletTransactionDto walletTransactionDto) {
        var wallet = walletService.deposit(walletTransactionDto);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Wallet> withDraw(@RequestBody @Valid WalletTransactionDto walletTransactionDto) {
        var wallet = walletService.withDraw(walletTransactionDto);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<PaymentTransaction>> getWalletStatement(@RequestBody @Valid WalleyKeyDto walletKey) {
        var walletStatement = walletService.getWalletStatement(walletKey);
        return ResponseEntity.ok(walletStatement);
    }
}
