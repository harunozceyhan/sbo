package com.smart.sbo.domain.beden;

import lombok.Data;

import java.util.List;
import javax.persistence.Table;
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
import com.smart.sbo.annotation.Metadata;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Metadata("worker")
@EqualsAndHashCode(callSuper = true)
@Table(name = "worker", schema = "postgres")
public class Worker extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 40)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    private String kodu;

    @ManyToOne()
    @RestResource(exported = false)
    @JoinColumn(name = "siparis_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Siparis siparis;

    @JsonIgnore
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Card> cards;
}