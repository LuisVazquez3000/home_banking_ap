package com.mindhub.homebanking.Controllers;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository repoClient;


    @RequestMapping("/clients")
    public List<ClientDTO>getClients(){
        return repoClient.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }


}
