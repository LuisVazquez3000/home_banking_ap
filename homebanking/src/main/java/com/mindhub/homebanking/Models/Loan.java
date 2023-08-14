package com.mindhub.homebanking.Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private double maxAmount;

    @ElementCollection
    @Column(name = "payments")
    private List<Integer> payments = new ArrayList<>();


    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan>clients = new HashSet<>();


    public Loan() {
    }

    public Loan(long id, String name, double maxAmount, List<Integer> payments) {
        this.id = id;
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }


    public Set<ClientLoan> getClientsLoans() {
        return clients;
    }

   public void addClientLoans(ClientLoan clientLoan){
        clientLoan.setLoan(this);
        clients.add(clientLoan);
   }

    public List<Client> getClients(){
        return clients.stream().map(clientLoan -> clientLoan.getClient()).collect(Collectors.toList());
    }
}
