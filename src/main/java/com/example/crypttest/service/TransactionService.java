package com.example.crypttest.service;

import com.example.crypttest.dto.EncryptedTransactionDto;
import com.example.crypttest.dto.TransactionDto;
import com.example.crypttest.entity.Transaction;
import com.example.crypttest.repository.TransactionRepository;
import com.example.crypttest.utils.EncryptUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class TransactionService implements IGeneralService<Transaction>{

    private final TransactionRepository transactionRepository;


    @Override
    public Transaction getById(Long id) {

        return null;
    }

    @Override
    public Iterable<Transaction> getAll() {
        return null;
    }

    @Override
    public Transaction create(Transaction transaction) {


        return null;
    }

    public Map<String, String> encryptTransaction(TransactionDto transaction){
        Map<String, String> map = new HashMap<>();
        byte[] objectByte = SerializationUtils.serialize(transaction);
        try {
            map.put("id", EncryptUtils.RSAEncrypt(String.valueOf(transaction.getId())));
            map.put("account", EncryptUtils.RSAEncrypt(transaction.getAccount()));
            map.put("inDebt", EncryptUtils.RSAEncrypt(String.valueOf(transaction.getInDebt())));
            map.put("have", EncryptUtils.RSAEncrypt(String.valueOf(transaction.getHave())));
            map.put("transactionId", EncryptUtils.RSAEncrypt(String.valueOf(Math.random())));
            map.put("receiveAccount", EncryptUtils.RSAEncrypt(String.valueOf(transaction.getReceiveAccount())));
        }catch (Exception e){

        }
        return map;
    }

    public String encryptTrans(TransactionDto transactionDto) throws Exception {
        return EncryptUtils.RSAEncrypt(transactionDto);
    }

    public Map<String, String> createTransaction(EncryptedTransactionDto encryptedTransactionDto){
        Map<String, String> map = new HashMap<>();

        try {
            String transactionId = EncryptUtils.RSADecrypt(encryptedTransactionDto.getTransactionId());
            Transaction sendTransaction = Transaction.builder()
                    .transactionId(EncryptUtils.AESEncrypt(transactionId))
                    .account(EncryptUtils.RSADecrypt(encryptedTransactionDto.getAccount()))
                    .inDebt(Float.parseFloat(EncryptUtils.RSADecrypt(encryptedTransactionDto.getInDebt())))
                    .have(Float.parseFloat(EncryptUtils.RSADecrypt(encryptedTransactionDto.getHave())))
                    .build();
            Transaction receiveTransaction = Transaction.builder()
                    .transactionId(EncryptUtils.AESEncrypt(transactionId))
                    .have(Float.parseFloat(EncryptUtils.RSADecrypt(encryptedTransactionDto.getInDebt())))
                    .inDebt(Float.parseFloat(EncryptUtils.RSADecrypt(encryptedTransactionDto.getHave())))
                    .account(EncryptUtils.RSADecrypt(encryptedTransactionDto.getReceiveAccount()))
                    .build();
            transactionRepository.save(sendTransaction);
            transactionRepository.save(receiveTransaction);
            log.info("Mã giao dịch: ?" + " Tài khoản gửi: " + EncryptUtils.RSADecrypt(encryptedTransactionDto.getAccount())  +
                    " Tài khoản nhận: " + EncryptUtils.RSADecrypt(encryptedTransactionDto.getReceiveAccount()) + " Số tiền: ?" + " Thời gian giao dịch: " + new Date());
        }catch (Exception e) {
            log.info("Lỗi giao dịch!" + e.getMessage());

        }

        return map;
    }


}
