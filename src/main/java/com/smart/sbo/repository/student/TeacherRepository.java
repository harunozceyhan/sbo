package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.Teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "teacher")
public interface TeacherRepository extends JpaRepository<Teacher, UUID>, JpaSpecificationExecutor<Teacher> {

    @RestResource(path = "teacher")
    public Page<Teacher> findAllByNameContainsIgnoreCaseAndSurnameContainsIgnoreCaseAndCodeContainsIgnoreCase(
            @Param("name") String name, @Param("surname") String surname, @Param("code") String code, Pageable p);

}