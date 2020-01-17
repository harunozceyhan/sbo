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
import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.Metadata;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "classroom", schema = "postgres")
@Metadata(value = "classroom", title = "classroomList", detailTitleKey = "name", baseUrl = "classroom", getUrl = "classroom/search/classroom", responseKey = "classrooms")
public class Classroom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 20)
    @Length(min = 1, max = 20)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 50)
    private String name;

    @NotNull
    @Column(nullable = false, length = 5)
    @Length(min = 1, max = 5)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 25)
    private String code;

    @NotNull
    @Column(nullable = false)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 20)
    private Integer level;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.PERSIST)
    private List<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<ClassInventory> classInventories;

}