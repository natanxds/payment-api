package com.payment.payment.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_wallet_type")
public class WalletType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public enum Enum {
        USER(1L, "user"),
        MERCHANT(2L, "merchant");

        private Long id;
        private String description;

        Enum(Long id, String description) {
            this.id = id;
            this.description = description;
        }

        public WalletType get() {
            return new WalletType(id, description);
        }
    }
}
