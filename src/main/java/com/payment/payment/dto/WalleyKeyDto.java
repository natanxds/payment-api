package com.payment.payment.dto;

import jakarta.validation.constraints.NotNull;

public record WalleyKeyDto(@NotNull String walletKey) {
}
