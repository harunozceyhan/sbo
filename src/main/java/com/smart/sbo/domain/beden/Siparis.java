package com.smart.sbo.domain.beden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.smart.sbo.domain.base.BaseEntity;

import org.springframework.data.rest.core.annotation.RestResource;

@Table(name = "siparis", schema = "postgres")
@Entity
public class Siparis extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_siparis")
    @SequenceGenerator(name = "seq_siparis", sequenceName = "seq_siparis", allocationSize = 1,initialValue=1)
    private Long id;

    @NotNull
    @Column(nullable=false, length=40)
    private String adi;
    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

    @OneToOne
    @NotNull
    @RestResource(path = "ops", rel = "ops", exported = false)
    private Operation operation;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getAdi() {
      return adi;
    }

    public void setAdi(String adi) {
      this.adi = adi;
    }

    public String getKodu() {
      return kodu;
    }

    public void setKodu(String kodu) {
      this.kodu = kodu;
    }

    public Operation getOperation() {
      return operation;
    }

    public void setOperation(Operation operation) {
      this.operation = operation;
    }

}