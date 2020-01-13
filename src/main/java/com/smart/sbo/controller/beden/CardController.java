package com.smart.sbo.controller.beden;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import com.smart.sbo.domain.beden.Card;
import com.smart.sbo.service.interfc.beden.CardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryRestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    PagedResourcesAssembler<Card> pagedResourcesAssembler;

    @RequestMapping(path = "/getcard", method = RequestMethod.GET, produces = "application/hal+json")
    public ResponseEntity<PagedModel<EntityModel<Card>>> getCard(@Param("adi") String adi, Pageable pageable) {
        PagedModel<EntityModel<Card>> collModel = pagedResourcesAssembler.toModel(cardService.getCards(pageable));
        return ResponseEntity.ok(collModel);
    }
    /*
     * @GetMapping("/{id}") public Card getCard(@PathVariable UUID id) { return
     * cardService.getCard(id); }
     * 
     * @PostMapping("/") public ResponseEntity<Card>
     * createQuestion(@Valid @RequestBody Card card) { return new
     * ResponseEntity<Card>(cardService.createCard(card), HttpStatus.CREATED); }
     * 
     * @PutMapping("/{id}") public ResponseEntity<Card> updateCard(@PathVariable
     * UUID id, @Valid @RequestBody Card card) { return new
     * ResponseEntity<Card>(cardService.updateCard(id, card), HttpStatus.OK); }
     * 
     * @DeleteMapping("/{id}") public ResponseEntity<?> deleteCard(@PathVariable
     * UUID id) { cardService.deleteCard(id); return new
     * ResponseEntity<>(HttpStatus.NO_CONTENT); }
     */
}