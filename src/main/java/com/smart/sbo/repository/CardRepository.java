package com.smart.sbo.repository;

import java.util.UUID;
import com.smart.sbo.domain.beden.Card;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {
}