package com.smart.sbo.domain.student;

import lombok.Data;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.model.base.BaseEntity;
import com.smart.annotation.MetaColumn;
import com.smart.annotation.Metadata;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "lesson", schema = "postgres")
@Metadata(value = "lesson", title = "lessonList", detailTitleKey = "name", baseUrl = "lesson", getUrl = "lesson/search/lesson", responseKey = "lessons")
public class Lesson extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 20)
    @Length(min = 1, max = 20)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 65)
    private String name;

    @NotNull
    @Column(nullable = false, length = 5)
    @Length(min = 1, max = 5)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 30)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<TeacherLessons> teacherLessons;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<StudentLessons> studentLessons;
}