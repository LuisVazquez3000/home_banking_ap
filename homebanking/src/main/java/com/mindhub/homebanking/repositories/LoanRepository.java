package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
