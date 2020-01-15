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
@Table(name = "class_inventroy", schema = "postgres")
@Metadata(value = "classInventory", title = "classInventoryList", detailTitleKey = "name", baseUrl = "classInventory", getUrl = "classInventory/search/classInventory", responseKey = "classInventorys")
public class ClassInventory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 30)
    @Length(min = 1, max = 30)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 50)
    private String name;

    @NotNull
    @Column(nullable = false, length = 5)
    @Length(min = 1, max = 5)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 25)
    private String code;

    @NotNull
    @Column(nullable = false)
    @Length(min = 1, max = 4)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 20)
    private Integer count;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "classroom_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(url = "classroom", responseKey = "classrooms", itemText = "name")
    private Classroom classroom;

}