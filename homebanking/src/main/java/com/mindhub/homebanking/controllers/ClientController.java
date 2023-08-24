package com.mindhub.homebanking.controllers;


import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientRepository repoClient;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/clients/current", method = RequestMethod.GET)
    public ClientDTO getClientCurrent(Authentication authentication){
        return new ClientDTO(repoClient.findByEmail(authentication.getName()));
    }


    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public List<ClientDTO>getClients(){
        return repoClient.findAll().stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){
        return new ClientDTO(repoClient.findById(id).orElse(null));
    }

//    public void getClient2(Long id){
//        repoClient.findAll().stream().filter(client-> Objects.equals(client.getId(), id));
//    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object>register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password
            ){
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }
        if (repoClient.findByEmail(email) != null){
            return new ResponseEntity<>("email already exists", HttpStatus.FORBIDDEN);
        }


        repoClient.save(new Client(
                firstName,
                lastName,
                email,
                passwordEncoder.encode(password)
        ));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
