package com.jpmc.midascore.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TransactionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID txnId;

    private float amount;

    private float incentive;

    @ManyToOne
    private UserRecord sender;

    @ManyToOne
    private UserRecord receiver;

    public UUID getTxnId() {
        return txnId;
    }

    public TransactionRecord() {
    }

    public TransactionRecord(float amount, UserRecord sender, UserRecord receiver) {
        this.amount = amount;
        this.sender = sender;
        this.receiver = receiver;
    }

    public void setTxnId(UUID txnId) {
        this.txnId = txnId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public UserRecord getSender() {
        return sender;
    }

    public void setSender(UserRecord sender) {
        this.sender = sender;
    }

    public UserRecord getReceiver() {
        return receiver;
    }

    public void setReceiver(UserRecord receiver) {
        this.receiver = receiver;
    }

    public float getIncentive() {
        return incentive;
    }

    public void setIncentive(float incentive) {
        this.incentive = incentive;
    }
}
