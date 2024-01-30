package com.example.crypttest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncryptedTransactionDto {
    private String id;
    private String transactionId;
    private String account;

    private String inDebt;
    private String have;

    private String receiveAccount;



}
