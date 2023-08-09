package com.mindhub.homebanking.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.mindhub.homebanking.dtos.AccountDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import java.util.Set;
import java.util.HashSet;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    public long id;
    private String firstName;
    private String lastName;

    private String email;


    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    Set<Account> accounts = new HashSet<>();


    public Client() {
    }

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  long getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }

}
