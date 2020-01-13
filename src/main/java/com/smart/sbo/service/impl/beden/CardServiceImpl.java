package com.smart.sbo.service.impl.beden;

import java.util.List;
import java.util.UUID;

import com.smart.sbo.domain.beden.Card;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import com.smart.sbo.repository.CardRepository;
import org.springframework.data.domain.Pageable;
import com.smart.sbo.service.interfc.beden.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public Page<Card> getCards(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    public Card getCard(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found card: " + id.toString()));
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public Card updateCard(UUID id, Card card) {
        return cardRepository.findById(id).map(cardObj -> {
            cardObj.setAdi(card.getAdi());
            cardObj.setKodu(card.getKodu());
            cardObj.setWorker(card.getWorker());
            return cardRepository.save(cardObj);
        }).orElseThrow(() -> new ResourceNotFoundException("Couldn't found card: " + id.toString()));
    }

    public ResponseEntity<?> deleteCard(UUID id) {
        return cardRepository.findById(id).map(card -> {
            cardRepository.delete(card);
            return ResponseEntity.noContent().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id.toString()));
    }
}