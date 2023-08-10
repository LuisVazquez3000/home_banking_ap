package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
