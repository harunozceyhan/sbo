package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.ClassInventory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "classInventory")
public interface ClassInventoryRepository
        extends JpaRepository<ClassInventory, UUID>, JpaSpecificationExecutor<ClassInventory> {

    @RestResource(path = "classroom")
    public Page<ClassInventory> findAllByClassroom_IdAndNameContainsIgnoreCaseAndCodeContainsIgnoreCase(
            @Param("id") UUID id, @Param("name") String name, @Param("code") String code, Pageable p);

}