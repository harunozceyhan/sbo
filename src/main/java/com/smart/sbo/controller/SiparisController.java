package com.smart.sbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import com.smart.sbo.domain.beden.Siparis;
import com.smart.sbo.repository.SiparisRepository;

@RepositoryRestController
@RequestMapping("/siparis")
public class SiparisController {

    @Autowired
    private SiparisRepository siparisRepository;

    @GetMapping("/getport")
    public ResponseEntity<Optional<Siparis>> getPort() {
        return ResponseEntity.ok(siparisRepository.findById(2l));
    }

}

