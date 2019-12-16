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
import com.smart.sbo.domain.base.BaseEntity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "card", schema = "postgres")
public class Card extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable=false, length=60)
    private String adi;

    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

    @ManyToOne()
    @JoinColumn(name = "worker_id", nullable = false)
    @RestResource(exported = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Worker worker;
}