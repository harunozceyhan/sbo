package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.Classroom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "classroom")
public interface ClassroomRepository extends JpaRepository<Classroom, UUID>, JpaSpecificationExecutor<Classroom> {

    @Query("SELECT c FROM Classroom c WHERE (:name = '' OR LOWER(c.name) LIKE LOWER(concat('%', concat(:name, '%')))) AND (:code = '' OR LOWER(c.code) LIKE LOWER(concat('%', concat(:code, '%')))) AND (:level IS NULL OR c.level = :level)")
    @RestResource(path = "classroom")
    public Page<Classroom> classroom(@Param("name") String name, @Param("code") String code,
            @Param("level") Integer level, Pageable p);

}