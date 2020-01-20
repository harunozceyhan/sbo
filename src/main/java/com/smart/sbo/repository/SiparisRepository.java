package com.smart.sbo.repository;

import java.util.UUID;
import com.smart.sbo.domain.beden.Siparis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RepositoryRestResource(path = "siparis")
public interface SiparisRepository extends JpaRepository<Siparis, UUID> {

    @RestResource(path = "siparis")
    public Page<Siparis> findAllByAdiContainsIgnoreCaseAndKoduContainsIgnoreCaseAndOperation_AdiContainsIgnoreCase(
            @Param("adi") String adi, @Param("kodu") String kodu, @Param("operationAdi") String operationAdi,
            Pageable p);

    @Query("SELECT s FROM Siparis s WHERE s.operation.id = :id AND (:adi = '' OR LOWER(s.adi) LIKE LOWER(concat('%', concat(:adi, '%')))) AND "
            + "(:kodu = '' OR LOWER(s.kodu) LIKE LOWER(concat('%', concat(:kodu, '%')))) AND "
            + "(:startOrderDate = '' OR :startOrderDate IS NULL OR to_date(to_char(s.orderDate, 'DD-MM-YYYY'),'DD-MM-YYYY') >= to_date(:startOrderDate,'DD-MM-YYYY')) AND "
            + "(:endOrderDate = '' OR :endOrderDate IS NULL OR to_date(to_char(s.orderDate, 'DD-MM-YYYY'),'DD-MM-YYYY') <= to_date(:endOrderDate,'DD-MM-YYYY'))")
    @RestResource(path = "operation")
    public Page<Siparis> operation(@Param("id") UUID id, @Param("adi") String adi, @Param("kodu") String kodu,
            @Param("startOrderDate") String startOrderDate, @Param("endOrderDate") String endOrderDate, Pageable p);

    @RestResource(path = "name")
    public Page<Siparis> findAllByAdiContainsIgnoreCase(@Param("adi") String adi, Pageable p);
}