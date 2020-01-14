package com.smart.sbo.domain.beden;

import lombok.Data;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.model.base.BaseEntity;
import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.Metadata;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "operation", schema = "postgres")
@Metadata(value = "operation", title = "operationList", detailTitleKey = "adi", baseUrl = "operation", getUrl = "operation/operation", responseKey = "operations")
public class Operation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Operation(String adi, String kodu, Boolean active) {
        this.adi = adi;
        this.kodu = kodu;
        this.active = active;
    }

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @Length(min = 1, max = 10)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    private String kodu;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Istanbul")
    @Temporal(TemporalType.DATE)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    Date operationDate;

    @NotNull
    @Column(columnDefinition = "boolean default true", nullable = false)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 35)
    private Boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    private List<Siparis> siparises;

}