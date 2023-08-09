package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    private Client account;
    public long id;
    private String firstName;
    private String lastName;

    private String email;

    private Set<AccountDTO> accounts;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts = client.getAccounts().stream().map(element -> new AccountDTO(element)).collect(Collectors.toSet());
    }

    public Client getAccount() {
        return account;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }
}
