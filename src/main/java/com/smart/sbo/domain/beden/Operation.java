package com.smart.sbo.domain.beden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.smart.sbo.domain.base.BaseEntity;

@Table(name = "operation", schema = "postgres")
@Entity
public class Operation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_operation")
    @SequenceGenerator(name = "seq_operation", sequenceName = "postgres.seq_operation", allocationSize = 1,initialValue=1)
    private Long id;

    @NotNull
    @Column(nullable=false, length=40)
    private String adi;
    @NotNull
    @Column(nullable=false, length=10)
    private String kodu;

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

}