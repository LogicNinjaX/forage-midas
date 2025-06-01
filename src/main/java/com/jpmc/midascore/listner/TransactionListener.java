package com.jpmc.midascore.listner;


import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.service.TransactionHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final TransactionHandler transactionHandler;

    public TransactionListener(TransactionHandler transactionHandler) {
        this.transactionHandler = transactionHandler;
    }

    @KafkaListener(topics = "my-kafka-topic", groupId = "group1")
    public void listen(Transaction transaction){
        var txn = transactionHandler.process(transaction);
        System.out.println(txn);
    }
}
