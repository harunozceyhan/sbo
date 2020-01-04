package com.smart.sbo.repository;

import java.util.UUID;
import com.smart.sbo.domain.beden.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RepositoryRestResource(path = "siparis")
public interface SiparisRepository extends JpaRepository<Siparis, UUID> {

    @RestResource(path = "siparis")
    public Page<Siparis> findAllByAdiContainsIgnoreCaseAndKoduContainsIgnoreCaseAndOperation_AdiContainsIgnoreCase(
            @Param("adi") String adi, @Param("kodu") String kodu, @Param("operationAdi") String operationAdi,
            Pageable p);
}