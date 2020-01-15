package com.smart.sbo.domain.student;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Column;
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
import org.hibernate.validator.constraints.Length;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "student_lesson", schema = "postgres")
@Metadata(value = "studentLesson", title = "studentLessonList", baseUrl = "studentLesson", getUrl = "studentLesson/search/studentLesson", responseKey = "studentLessons")
public class StudentLessons extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "student_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 40, formType = "combobox", url = "student", responseKey = "students", itemText = "name", tableValue = "student.name", searchKey = "studentName")
    private Student student;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "lesson_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 40, formType = "combobox", url = "lesson", responseKey = "lessons", itemText = "name", tableValue = "lesson.name", searchKey = "lessonName")
    private Lesson lesson;

    @NotNull
    @Column(nullable = false)
    @Length(min = 1, max = 4)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 10)
    private Float grade;
}