package com.smart.sbo.domain.beden;

import lombok.Data;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smart.model.base.BaseEntity;
import com.smart.sbo.annotation.Metadata;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Metadata("operation")
@EqualsAndHashCode(callSuper = true)
@Table(name = "operation", schema = "postgres")
public class Operation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public Operation(String adi, String kodu) {
        this.adi = adi;
        this.kodu = kodu;
    }

    @NotNull
    @Column(nullable = false, length = 40)
    @Length(min = 1, max = 40)
    private String adi;

    @NotNull
    @Column(nullable = false, length = 10)
    @Length(min = 1, max = 10)
    private String kodu;

    @JsonIgnore
    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    private List<Siparis> siparises;

}