package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.Models.Card;
import com.mindhub.homebanking.Models.CardColor;
import com.mindhub.homebanking.Models.CardType;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mindhub.homebanking.utils.randomCardNumber.randomCardNumber;
import static com.mindhub.homebanking.utils.randomNumber.getRandomNumber;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/cards")
    public Set<CardDTO> cards(){
    return cardRepository.findAll().stream().map(card -> new CardDTO(card)).collect(Collectors.toSet());
    }

    @PostMapping("/clients/current/cards")
    public ResponseEntity<Object> createCard(@RequestParam CardType cardType, @RequestParam CardColor cardColor, Authentication authentication){
        Client client = clientRepository.findByEmail(authentication.getName());

        List<Card> cardsFilter = client.getCards().stream().filter(card -> card.getType() == cardType).collect(Collectors.toList());

        if (cardsFilter.size() >= 3){
            return new ResponseEntity<>("Maximum number of cards reached", HttpStatus.FORBIDDEN);
        }

        String cardNumber;
        do{
            cardNumber = randomCardNumber();
        } while (cardRepository.existsByNumber(cardNumber));

        Card card = new Card( );
        card.setCardHolder(client.getFirstName() + " "+client.getLastName());
        card.setType(cardType);
        card.setColor(cardColor);
        card.setNumber(cardNumber);
        card.setCvv(getRandomNumber(0,999));
        card.setFromDate(LocalDate.now());
        card.setThruDate(LocalDate.now().plusYears(5));
        client.addCard(card);
        cardRepository.save(card);
        return new ResponseEntity<>("Card created successfully", HttpStatus.CREATED);
    }
}
