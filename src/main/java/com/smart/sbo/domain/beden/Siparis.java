package com.smart.sbo.domain.beden;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import com.smart.model.base.BaseEntity;
import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.MetaTab;
import com.smart.sbo.annotation.Metadata;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "siparis", schema = "postgres")
@Metadata(value = "siparis", title = "siparisList", detailTitleKey = "adi", baseUrl = "siparis", getUrl = "siparis/search/siparis", responseKey = "siparises")
public class Siparis extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 30)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @Length(min = 1, max = 10)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 15)
    private String kodu;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Istanbul")
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 20, formType = "datetimepicker")
    Date orderDate;

    @NotNull
    @ManyToOne
    @RestResource(exported = false)
    @JoinColumn(name = "operation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 30, formType = "autocomplete", url = "operation/search/name", responseKey = "operations", itemText = "adi", tableValue = "operation.adi", searchKey = "operationAdi")
    private Operation operation;

    @JsonIgnore
    @OneToMany(mappedBy = "siparis", cascade = CascadeType.ALL)
    @MetaTab()
    private List<Worker> workers;
}