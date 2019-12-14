package com.smart.sbo.domain.beden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.smart.sbo.domain.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Table(name = "operation", schema = "postgres")
public class Operation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable=false, length=40)
    private String adi;
    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

}