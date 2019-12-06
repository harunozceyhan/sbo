package com.smart.sbo.domain.beden;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.smart.sbo.domain.base.BaseEntity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name = "worker", schema = "postgres")
public class Worker extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_worker")
    @SequenceGenerator(name = "seq_worker", sequenceName = "seq_worker", allocationSize = 1, initialValue=1)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(final String adi) {
        this.adi = adi;
    }

    public String getKodu() {
        return kodu;
    }

    public void setKodu(final String kodu) {
        this.kodu = kodu;
    }

    public Siparis getSiparis() {
        return siparis;
    }

    public void setSiparis(Siparis siparis) {
        this.siparis = siparis;
    }

    

}