package com.smart.sbo.domain.student;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "student", schema = "postgres")
@Metadata(value = "student", title = "studentList", detailTitleKey = "name", baseUrl = "student", getUrl = "student/search/student", responseKey = "students")
public class Student extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 30)
    @Length(min = 1, max = 12)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 20)
    private Integer studentNumber;

    @NotNull
    @Column(nullable = false, length = 30)
    @Length(min = 1, max = 30)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 20)
    private String name;

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 20)
    private String surname;

    @NotNull
    @Column(nullable = false)
    @Length(min = 1, max = 4)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 10)
    private Integer grade;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "classroom_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 15, formType = "combobox", url = "classroom", responseKey = "classrooms", itemText = "name", tableValue = "classroom.name", searchKey = "classroomName")
    private Classroom classroom;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Istanbul")
    @MetaColumn()
    Date entryDate;

    @Column(nullable = true, length = 6)
    @Length(min = 1, max = 6)
    @MetaColumn()
    private String gender;

    @NotNull
    @Column(columnDefinition = "boolean default true", nullable = false)
    @MetaColumn(sortable = true, showInTable = true, width = 10)
    private Boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentLessons> studentLessons;

}