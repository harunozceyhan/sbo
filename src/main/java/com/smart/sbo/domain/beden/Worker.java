package com.smart.sbo.domain.beden;

import lombok.Data;

import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.AllArgsConstructor;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import com.smart.model.base.BaseEntity;
import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.Metadata;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "worker", schema = "postgres")
@Metadata(value = "worker", title = "workerList", detailTitleKey = "adi", baseUrl = "worker", getUrl = "worker/search/worker", responseKey = "workers")
public class Worker extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 40)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 30)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 15)
    private String kodu;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Istanbul")
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 20, searchKey = "WorkDate")
    Date workDate;

    @NotNull
    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "siparis_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = false, showInTable = true, width = 30, formType = "combobox", url = "siparis", responseKey = "siparises", itemText = "adi", tableValue = "siparis.adi", searchKey = "siparisAdi")
    private Siparis siparis;

    @JsonIgnore
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Card> cards;
}