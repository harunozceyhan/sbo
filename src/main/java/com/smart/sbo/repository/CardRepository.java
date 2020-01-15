package com.smart.sbo.repository;

import java.util.UUID;
import com.smart.sbo.domain.beden.Card;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "card")
public interface CardRepository extends JpaRepository<Card, UUID> {
    @RestResource(path = "card")
    public Page<Card> findAllByAdiContainsIgnoreCaseAndKoduContainsIgnoreCaseAndWorker_AdiContainsIgnoreCase(
            @Param("adi") String adi, @Param("kodu") String kodu, @Param("workerAdi") String workerAdi, Pageable p);

}