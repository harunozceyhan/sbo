package com.smart.sbo.controller;

import javax.persistence.EntityManager;
import com.smart.sbo.service.interfc.beden.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metadata")
public class MetaDataController {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MetaDataService metaDataService;

    @GetMapping("/{name}")
    public ResponseEntity<Object> metadataName(@PathVariable String name) {
        return ResponseEntity.ok(metaDataService.getMetaDataOfClass(name));
    }
}