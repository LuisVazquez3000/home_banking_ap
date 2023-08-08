package com.mindhub.homebanking;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.AccountRepository;


@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository){

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


});

}


}
