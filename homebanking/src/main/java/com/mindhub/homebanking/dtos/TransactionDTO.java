package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.Models.Transaction;

import com.mindhub.homebanking.Models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    public long id;

    public Double amount;
    public String description;
    public LocalDateTime date;

    public TransactionType type;


    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.type = transaction.getType();
    }


    public long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public TransactionType getType() {
        return type;
    }



}
