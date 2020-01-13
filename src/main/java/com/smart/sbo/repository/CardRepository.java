package com.smart.sbo.repository;

import java.util.UUID;
import com.smart.sbo.domain.beden.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "card")
public interface CardRepository extends JpaRepository<Card, UUID> {

}