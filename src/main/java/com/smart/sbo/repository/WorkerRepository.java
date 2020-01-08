package com.smart.sbo.repository;

import java.util.UUID;

import com.smart.sbo.domain.beden.Worker;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "worker")
public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    @RestResource(path = "worker")
    public Page<Worker> findAllByAdiContainsIgnoreCaseAndKoduContainsIgnoreCase(@Param("adi") String adi,
            @Param("kodu") String kodu, Pageable p);

    @RestResource(path = "name")
    public Page<Worker> findAllByAdiContainsIgnoreCase(@Param("adi") String adi, Pageable p);
}