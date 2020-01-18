package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.StudentLessons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "studentLesson")
public interface StudentLessonsRepository
        extends JpaRepository<StudentLessons, UUID>, JpaSpecificationExecutor<StudentLessons> {

    @RestResource(path = "lesson")
    public Page<StudentLessons> findAllByLesson_IdAndStudent_NameContainsIgnoreCase(@Param("id") UUID id,
            @Param("studentName") String studentName, Pageable p);

    @RestResource(path = "student")
    public Page<StudentLessons> findAllByStudent_IdAndLesson_NameContainsIgnoreCase(@Param("id") UUID id,
            @Param("lessonName") String lessonName, Pageable p);

}