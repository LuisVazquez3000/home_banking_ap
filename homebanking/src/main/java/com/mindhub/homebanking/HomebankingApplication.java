package com.mindhub.homebanking;

import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.AccountRepository;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData(ClientRepository clientRepository,
								  AccountRepository accountRepository,
								  TransactionRepository transactionRepository,
								  LoanRepository loanRepository
){

return (args -> {
		Client client1 = new Client();
		client1.setFirstName("Melba");
		client1.setLastName("Morel");
		client1.setEmail("melba@mindhub.com");
		clientRepository.save(client1);

		Account account1 = new Account();
		account1.setNumber("VIN001");
		account1.setCreationDate(LocalDate.now());
		account1.setBalance(5000.00);
		client1.addAccount(account1);
		accountRepository.save(account1);

		Account account2 = new Account();
		account2.setNumber("VIN002");
		account2.setCreationDate(LocalDate.now().plusDays(1));
		account2.setBalance(7500.00);
		client1.addAccount(account2);
		accountRepository.save(account2);

		Transaction transaction1 = new Transaction();
		transaction1.setAmount(10000.00);
		transaction1.setDescription("Cobranzas varias");
		transaction1.setDate(LocalDateTime.now());
		transaction1.setType(TransactionType.CREDIT);
		account1.addTransaction(transaction1);
		transactionRepository.save(transaction1);


		Transaction transaction2 = new Transaction();
		transaction2.setAmount(-1000.00);
		transaction2.setDescription("pagos a proveedores");
		transaction2.setDate(LocalDateTime.now());
		transaction2.setType(TransactionType.DEBIT);
		account1.addTransaction(transaction2);
		transactionRepository.save(transaction2);


		Loan loan1 = new Loan();
		loan1.setName("Hipotecario");
		loan1.setMaxAmount(500000.00);
		loan1.setPayments(List.of(12,24,36,48,60));
		loanRepository.save(loan1);

		Loan loan2 = new Loan();
		loan2.setName("Personal");
		loan2.setMaxAmount(100000.00);
		loan2.setPayments(List.of(6,12,24));
		loanRepository.save(loan2);

		Loan loan3 = new Loan();
		loan3.setName("Automotriz");
		loan3.setMaxAmount(300000.00);
		loan3.setPayments(List.of(6,12,24,36));
		loanRepository.save(loan3);


});

}


}
