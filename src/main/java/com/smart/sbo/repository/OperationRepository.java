package com.smart.sbo.repository;

import java.util.UUID;

import com.smart.sbo.domain.beden.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "operation")
interface OperationRepository extends JpaRepository<Operation, UUID> {
    @RestResource(path = "operation")
    public Page<Operation> findAllByAdiContainsIgnoreCaseAndKoduContainsIgnoreCase(@Param("adi") String adi,
            @Param("kodu") String kodu, Pageable p);

    @RestResource(path = "name")
    public Page<Operation> findAllByAdiContainsIgnoreCase(@Param("adi") String adi, Pageable p);

}