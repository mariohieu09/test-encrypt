package com.example.crypttest.controller;

import com.example.crypttest.dto.EncryptedTransactionDto;
import com.example.crypttest.dto.TransactionDto;
import com.example.crypttest.entity.Transaction;
import com.example.crypttest.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping("/encrypted-transaction")
    public Map<String, String> encryptedTransaction(@RequestBody TransactionDto transaction){
        return transactionService.encryptTransaction(transaction);
    }

    @PostMapping("/create-transaction")
    public Map<String, String> createTransaction(@RequestBody EncryptedTransactionDto encryptedTransactionDto) throws Exception {
        return transactionService.createTransaction(encryptedTransactionDto);
    }

}
