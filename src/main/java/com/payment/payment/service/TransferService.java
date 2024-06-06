package com.payment.payment.service;

import com.payment.payment.dto.TransferDto;
import com.payment.payment.entity.PaymentTransaction;
import com.payment.payment.entity.Transfer;
import com.payment.payment.entity.Wallet;
import com.payment.payment.exception.InsufficientBalanceException;
import com.payment.payment.exception.TransferNotAllowedForWalletTypeException;
import com.payment.payment.exception.TransferNotAuthorizedException;
import com.payment.payment.exception.WalletNotFoundException;
import com.payment.payment.repository.PaymentTransactionRepository;
import com.payment.payment.repository.TransferRepository;
import com.payment.payment.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    private final AuthorizationService authorizationService;

    private final NotificationService notificationService;

    private final WalletRepository walletRepository;

    private final PaymentTransactionRepository paymentTransactionRepository;

    public TransferService(TransferRepository transferRepository, AuthorizationService authorizationService, NotificationService notificationService, WalletRepository walletRepository, PaymentTransactionRepository paymentTransactionRepository) {
        this.transferRepository = transferRepository;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.walletRepository = walletRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    @Transactional
    public Transfer transfer(TransferDto transferDto) {
        var sender = walletRepository.findByCpfCnpjOrEmail(transferDto.payer(), transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findByCpfCnpjOrEmail(transferDto.payee(), transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        PaymentTransaction paymentTransactionSender = new PaymentTransaction(sender, PaymentTransaction.TransactionType.WITHDRAW, transferDto.value(), LocalDateTime.now());
        paymentTransactionRepository.save(paymentTransactionSender);
        PaymentTransaction paymentTransactionReceiver = new PaymentTransaction(receiver, PaymentTransaction.TransactionType.DEPOSIT, transferDto.value(), LocalDateTime.now());
        paymentTransactionRepository.save(paymentTransactionReceiver);
        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender) {
        if (!sender.isTransferAllowedForWalletType()) {
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEnough(transferDto.value())) {
            throw new InsufficientBalanceException(transferDto.value());
        }

        if (!authorizationService.isAuthorized(transferDto)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
