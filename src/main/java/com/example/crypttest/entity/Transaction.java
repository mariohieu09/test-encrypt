package com.example.crypttest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "transaction_id")
    private String transactionId;
    @Column(name = "account_number")
    private String account;

    private float inDebt;
    @Column(name = "have")
    private float have;
    @Column(name = "transaction_time")
    private Date time;

    @PrePersist
    private void Insert(){
        this.time = new Date();
    }
}
