package com.mindhub.homebanking.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.dtos.AccountDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;


@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy="client", fetch=FetchType.EAGER)
    private  Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> loans = new HashSet<>();

    public Client() {
    }

    public Client(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Client( String firstName, String lastName, String email) {

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


    public Set<Account> getAccounts() {
        return accounts;
    }


    public void addAccount(Account account) {
        account.setClient(this);
        accounts.add(account);
    }
    public Set<ClientLoan> getClientLoans(){
        return loans;
    }
    public void addClientLoans(ClientLoan clientLoan){
        clientLoan.setClient(this);
        loans.add(clientLoan);
    }
    @JsonIgnore
    public List<Loan> getLoans(){
        return loans.stream().map(clientLoan -> clientLoan.getLoan()).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", accounts=" + accounts +
                ", loans=" + loans +
                '}';
    }
}
