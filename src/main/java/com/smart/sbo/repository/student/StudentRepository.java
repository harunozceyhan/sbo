package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "student")
public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {

    @Query("SELECT s FROM Student s WHERE (:studentNumber = '' OR CAST(s.studentNumber AS string) LIKE %:studentNumber%) AND (:name = '' OR LOWER(s.name) LIKE LOWER(concat('%', concat(:name, '%')))) AND"
            + " (:surname = '' OR LOWER(s.surname) LIKE LOWER(concat('%', concat(:surname, '%')))) AND (:grade IS NULL OR s.grade = :grade) AND"
            + " (:classroomName = '' OR LOWER(s.classroom.name) LIKE LOWER(concat('%', concat(:classroomName, '%'))))")
    @RestResource(path = "student")
    public Page<Student> student(@Param("studentNumber") String studentNumber, @Param("name") String name,
            @Param("surname") String surname, @Param("grade") Integer grade,
            @Param("classroomName") String classroomName, Pageable p);

}