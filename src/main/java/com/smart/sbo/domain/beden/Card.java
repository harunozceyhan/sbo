package com.smart.sbo.domain.beden;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
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
@Table(name = "card", schema = "postgres")
@Metadata(value = "card", title = "cardList", detailTitleKey = "adi", baseUrl = "card", getUrl = "card/search/card", responseKey = "cards")
public class Card extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 60)
    @Length(min = 1, max = 60)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 40)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @Length(min = 1, max = 10)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 15)
    private String kodu;

    @ManyToOne()
    @JoinColumn(name = "worker_id", nullable = false)
    @RestResource(exported = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @MetaColumn(sortable = true, searchable = true, showInTable = true, width = 40, formType = "combobox", url = "worker", responseKey = "workers", itemText = "adi", tableValue = "worker.adi", searchKey = "workerAdi")
    private Worker worker;
}