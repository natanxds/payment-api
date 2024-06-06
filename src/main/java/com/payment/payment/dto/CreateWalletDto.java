package com.payment.payment.dto;

import com.payment.payment.entity.Wallet;
import com.payment.payment.entity.WalletType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWalletDto(
       @NotBlank String fullname,
       @NotBlank String cpf_cnpj,
       @NotBlank String email,
       @NotBlank String password,
       @NotNull WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(fullname, cpf_cnpj, email, password, walletType.get());
    }
}
