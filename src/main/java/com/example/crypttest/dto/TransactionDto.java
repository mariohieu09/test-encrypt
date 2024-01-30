package com.example.crypttest.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto implements Serializable {
    private Long id;
    private Long transactionId;
    private String account;

    private float inDebt;
    private float have;

    private String receiveAccount;


}
