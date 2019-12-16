package com.smart.sbo.controller.beden;

import java.util.UUID;
import javax.validation.Valid;
import com.smart.sbo.domain.beden.Card;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import com.smart.sbo.service.interfc.beden.CardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/")
    public Page<Card> getCars(Pageable pageable) {
        return cardService.getCards(pageable);
    }

    @GetMapping("/{id}")
    public Card getCard(@PathVariable UUID id) {
        return cardService.getCard(id);
    }

    @PostMapping("/")
    public ResponseEntity<Card> createQuestion(@Valid @RequestBody Card card) {
        return new ResponseEntity<Card>(cardService.createCard(card), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable UUID id, @Valid @RequestBody Card card) {
        return new ResponseEntity<Card>(cardService.updateCard(id, card), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}