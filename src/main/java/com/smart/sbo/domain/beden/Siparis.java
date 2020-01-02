package com.smart.sbo.domain.beden;

import lombok.Data;
import java.util.List;
import javax.persistence.Table;
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
import com.smart.sbo.annotation.Metadata;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Metadata("siparis")
@EqualsAndHashCode(callSuper = true)
@Table(name = "siparis", schema = "postgres")
public class Siparis extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @Length(min = 1, max = 10)
    private String kodu;

    @NotNull
    @ManyToOne
    @RestResource(exported = false)
    @JoinColumn(name = "operation_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Operation operation;

    @JsonIgnore
    @OneToMany(mappedBy = "siparis", cascade = CascadeType.ALL)
    private List<Worker> workers;
}