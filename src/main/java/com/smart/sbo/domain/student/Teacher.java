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
import com.smart.annotation.MetaTab;
import com.smart.annotation.Metadata;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "teacher", schema = "postgres")
@Metadata(value = "teacher", title = "teacherList", detailTitleKey = "name", baseUrl = "teacher", getUrl = "teacher/search/teacher", responseKey = "teachers")
public class Teacher extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 30)
    @Length(min = 1, max = 30)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    private String name;

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    private String surname;

    @NotNull
    @Column(nullable = false, length = 5)
    @Length(min = 1, max = 5)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 25)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @MetaTab("inline")
    private List<TeacherLessons> teacherLessons;
}