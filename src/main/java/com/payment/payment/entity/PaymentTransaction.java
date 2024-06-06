package com.payment.payment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_payment_transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public PaymentTransaction(Wallet wallet, TransactionType transactionType, BigDecimal amount, LocalDateTime now) {
        this.wallet = wallet;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = now;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }
}
