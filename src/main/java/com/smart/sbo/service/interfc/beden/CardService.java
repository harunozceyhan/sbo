package com.smart.sbo.service.interfc.beden;

import java.util.List;
import java.util.UUID;
import com.smart.sbo.domain.beden.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

/**
 * Created by harun on 14.05.2019.
 */
public interface CardService {
    List<Card> getCards();
    Page<Card> getCards(Pageable pageable);
    Card getCard(UUID id);
    Card createCard(Card card);
    Card updateCard(UUID id, Card card);
    ResponseEntity<?> deleteCard(UUID id);
}
