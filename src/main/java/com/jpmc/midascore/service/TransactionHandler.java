package com.jpmc.midascore.service;


import com.jpmc.midascore.entity.TransactionRecord;
import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TransactionHandler {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final IncentiveService incentiveService;

    public TransactionHandler(UserRepository userRepository, TransactionRepository transactionRepository, IncentiveService incentiveService) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.incentiveService = incentiveService;
    }


    public TransactionRecord process(Transaction transaction){

        UserRecord sender = userRepository.findById(transaction.getSenderId());
        UserRecord receiver = userRepository.findById(transaction.getRecipientId());

        TransactionRecord txn = null;

        if (sender == null && receiver == null) return null;

        if (sender.getBalance() >= transaction.getAmount()){
            float incentiveAmount = incentiveService.getIncentive(transaction);
            sender.setBalance(sender.getBalance() - transaction.getAmount());
            receiver.setBalance(receiver.getBalance() + transaction.getAmount() + incentiveAmount);

            TransactionRecord transactionRecord = new TransactionRecord(transaction.getAmount(), sender, receiver);

            userRepository.save(sender);
            userRepository.save(receiver);
            txn = transactionRepository.save(transactionRecord);
        }

        return txn;
    }
}
