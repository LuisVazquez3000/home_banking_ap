package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.randomNumber.getRandomNumber;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountRepository repoAccount;
    @Autowired
    private ClientRepository clientRepository;


    @RequestMapping("/accounts")
    public List<AccountDTO> getAccounts(){
        return repoAccount.findAll().stream().map(account -> new AccountDTO(account)).collect(Collectors.toList());
    }

    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return new AccountDTO(repoAccount.findById(id).orElse(null));
    }

    // /api/clients/current/accounts

    @PostMapping("/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication){
        String numberAccount;

        Client client = clientRepository.findByEmail(authentication.getName());
        if (client.getAccounts().size() >= 3){
            return new ResponseEntity<>("el tope de cuentas es de 3 cuentas ", HttpStatus.FORBIDDEN);
        }
        do {
            numberAccount = "VIN" + getRandomNumber(11111111,00000000);
        } while(repoAccount.existsByNumber(numberAccount));

        Account account = new Account();
        account.setNumber(numberAccount);
        account.setCreationDate(LocalDate.now());
        account.setBalance(0.00);
        client.addAccount(account);
        repoAccount.save(account);
        return new ResponseEntity<>("Cuenta Creada con exito",HttpStatus.CREATED);
    }


}
