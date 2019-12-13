package com.smart.sbo.repository;

import java.util.List;
import java.util.UUID;

import com.smart.sbo.domain.beden.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "worker")
public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    @RestResource(path = "adiStartsWith", rel = "adiStartsWith")
    public List<Worker> findAllByAdi(@Param("adi") String adi);
}