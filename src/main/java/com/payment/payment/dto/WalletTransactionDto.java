package com.payment.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WalletTransactionDto(@NotNull String walletKey, @DecimalMin("0.01") BigDecimal amount) {
}
