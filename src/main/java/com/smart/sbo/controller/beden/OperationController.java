package com.smart.sbo.controller.beden;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import com.smart.sbo.domain.beden.Operation;
import com.smart.sbo.service.interfc.beden.OperationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

@RepositoryRestController
@RequestMapping("/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    PagedResourcesAssembler<Operation> pagedResourcesAssembler;

    @RequestMapping(path = "/operation", method = RequestMethod.GET, produces = "application/hal+json")
    public ResponseEntity<PagedModel<EntityModel<Operation>>> get(@Param("adi") String adi, @Param("kodu") String kodu,
            @Param("startOperationDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startOperationDate,
            @Param("endOperationDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endOperationDate, Pageable p) {
        PagedModel<EntityModel<Operation>> collModel = pagedResourcesAssembler
                .toModel(operationService.getOperationList(adi, kodu, startOperationDate, endOperationDate, p));
        return ResponseEntity.ok(collModel);
    }
}