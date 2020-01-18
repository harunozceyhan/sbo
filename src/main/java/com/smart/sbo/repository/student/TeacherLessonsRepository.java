package com.smart.sbo.repository.student;

import java.util.UUID;
import com.smart.sbo.domain.student.TeacherLessons;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "teacherLesson")
public interface TeacherLessonsRepository
        extends JpaRepository<TeacherLessons, UUID>, JpaSpecificationExecutor<TeacherLessons> {

    @RestResource(path = "lesson")
    public Page<TeacherLessons> findAllByLesson_IdAndTeacher_NameContainsIgnoreCase(@Param("id") UUID id,
            @Param("teacherName") String teacherName, Pageable p);

    @RestResource(path = "teacher")
    public Page<TeacherLessons> findAllByTeacher_IdAndLesson_NameContainsIgnoreCase(@Param("id") UUID id,
            @Param("lessonName") String lessonName, Pageable p);

}