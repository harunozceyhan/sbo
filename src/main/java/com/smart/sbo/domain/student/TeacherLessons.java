package com.smart.sbo.domain.student;

import lombok.Data;
import javax.persistence.Table;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;

import com.smart.model.base.BaseEntity;
import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.Metadata;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "teacher_lesson", schema = "postgres")
@Metadata(value = "teacherLesson", title = "teacherLessonList", baseUrl = "teacherLesson", getUrl = "teacherLesson/search/teacherLesson", responseKey = "teacherLessons")
public class TeacherLessons extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 45, formType = "combobox", url = "teacher", responseKey = "teachers", itemText = "name", tableValue = "teacher.name", searchKey = "teacherName")
    private Teacher teacher;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 45, formType = "combobox", url = "lesson", responseKey = "lessons", itemText = "name", tableValue = "lesson.name", searchKey = "lessonName")
    private Lesson lesson;

}