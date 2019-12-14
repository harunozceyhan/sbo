package com.smart.sbo.domain.beden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.smart.sbo.domain.base.BaseEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "worker", schema = "postgres")
public class Worker extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable=false, length=40)
    private String adi;
    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

    @ManyToOne()
    @JoinColumn(name = "siparis_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @RestResource(exported = false)
    private Siparis siparis;
}