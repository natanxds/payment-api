package com.payment.payment.service;

import com.payment.payment.dto.CreateWalletDto;
import com.payment.payment.dto.WalletTransactionDto;
import com.payment.payment.dto.WalleyKeyDto;
import com.payment.payment.entity.PaymentTransaction;
import com.payment.payment.entity.Wallet;
import com.payment.payment.exception.InsufficientBalanceException;
import com.payment.payment.exception.WalletDataAlreadyExistsException;
import com.payment.payment.exception.WalletNotFoundException;
import com.payment.payment.repository.PaymentTransactionRepository;
import com.payment.payment.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    private final PaymentTransactionRepository paymentTransactionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

    public WalletService(WalletRepository walletRepository, PaymentTransactionRepository paymentTransactionRepository) {
        this.walletRepository = walletRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
    }

    public Wallet createWallet(CreateWalletDto dto) {
        var walletDb = walletRepository.findByCpfCnpjOrEmail(dto.cpf_cnpj(), dto.email());
        if (walletDb.isPresent()) {
            throw new WalletDataAlreadyExistsException("Cpf/Cnpj or email already exists.");
        }
        LOGGER.info("Creating wallet for: " + dto.cpf_cnpj() + " - " + dto.email());
        return walletRepository.save(dto.toWallet());
    }

    @Transactional
    public Wallet deposit(WalletTransactionDto walletTransactionDto) {
        var wallet = walletRepository.findByCpfCnpjOrEmail(walletTransactionDto.walletKey(), walletTransactionDto.walletKey())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found."));

        LOGGER.info("Depositing amount: " + walletTransactionDto.amount() + " to wallet: " + walletTransactionDto.walletKey());
        wallet.credit(walletTransactionDto.amount());
        PaymentTransaction paymentTransaction = new PaymentTransaction(wallet, PaymentTransaction.TransactionType.DEPOSIT, walletTransactionDto.amount(), LocalDateTime.now());
        paymentTransactionRepository.save(paymentTransaction);
        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet withDraw(WalletTransactionDto walletTransactionDto) {
        var wallet = walletRepository.findByCpfCnpjOrEmail(walletTransactionDto.walletKey(), walletTransactionDto.walletKey())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found."));

        if (!wallet.isBalanceEnough(walletTransactionDto.amount())) {
            throw new InsufficientBalanceException(walletTransactionDto.amount());
        }
        LOGGER.info("Withdrawing amount: " + walletTransactionDto.amount() + " from wallet: " + walletTransactionDto.walletKey());
        wallet.debit(walletTransactionDto.amount());
        PaymentTransaction paymentTransaction = new PaymentTransaction(wallet, PaymentTransaction.TransactionType.WITHDRAW, walletTransactionDto.amount(), LocalDateTime.now());
        paymentTransactionRepository.save(paymentTransaction);
        return walletRepository.save(wallet);
    }

    public List<PaymentTransaction> getWalletStatement(WalleyKeyDto walleyKeyDto) {
        var wallet = walletRepository.findByCpfCnpjOrEmail(walleyKeyDto.walletKey(), walleyKeyDto.walletKey())
                .orElseThrow(() -> new WalletNotFoundException("Wallet not found."));

        return paymentTransactionRepository.findAllByWalletId(wallet.getId());
    }

}
