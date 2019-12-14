package com.smart.sbo.domain.beden;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.sbo.domain.base.BaseEntity;
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
@Table(name = "siparis", schema = "postgres")
public class Siparis extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable=false, length=40)
    private String adi;
    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

    @ManyToOne
    @NotNull
    @RestResource(exported = false)
    private Operation operation;

    @OneToMany(mappedBy = "siparis", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Worker> workers;
}