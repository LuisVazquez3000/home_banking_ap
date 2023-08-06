package com.mindhub.homebanking;

import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

@Bean
public CommandLineRunner initData(ClientRepository clientRepository){

return (args -> {
Client client1 = new Client();
//		client1.setId(123);
		client1.setFirstName("Luis");
		client1.setLastName("Vazquez");
		client1.setEmail("luis@vazquez.com");

Client client2 = new Client();
//		client2.setId(321);
		client2.setFirstName("Martin");
		client2.setLastName("Cruz");
		client2.setEmail("martin@cruz.com");


		clientRepository.save(client1);
		clientRepository.save(client2);
});

}


}
