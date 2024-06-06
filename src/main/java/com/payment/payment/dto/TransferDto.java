package com.payment.payment.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransferDto(@DecimalMin("0.01") @NotNull BigDecimal value, @NotNull String payer, @NotNull String payee) {
}
