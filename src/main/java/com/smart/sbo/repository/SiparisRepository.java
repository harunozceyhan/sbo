package com.smart.sbo.repository;

import java.util.List;
import java.util.UUID;

import com.smart.sbo.domain.beden.Siparis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "siparis")
public interface SiparisRepository extends JpaRepository<Siparis, UUID> {
    @RestResource(path = "adiStartsWith", rel = "adiStartsWith")
    public List<Siparis> findAllByAdi(@Param("adi") String adi);
}