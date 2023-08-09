package com.mindhub.homebanking.Controllers;


import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository repoClient;


    @RequestMapping("/clients")
    public List<ClientDTO>getClients(){
        return repoClient.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @RequestMapping("/clients/{valor}")
    public ClientDTO getClient(@PathVariable Long valor){
        return new ClientDTO(repoClient.findById(valor).orElse(null));
    }

    public void getClient2(Long id){
        repoClient.findAll().stream().filter(client-> Objects.equals(client.getId(), id));
    }




}
