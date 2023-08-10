package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.Models.Transaction;
import com.mindhub.homebanking.Models.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    public long id;

    private double amount;
    private String description;
    private LocalDateTime date;

    private TransactionType type;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.type = transaction.getType();
    }

  /*  public TransactionDTO(Transaction element) {
    }*/

    public long getId() {
        return id;
    }

    public double getAmount() {
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