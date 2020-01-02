package com.smart.sbo.controller;

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import com.smart.sbo.annotation.Metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/metadata")
public class MetaDataController {

    @Autowired
    EntityManager entityManager;

    @GetMapping("/{name}")
    public String metadataName(@PathVariable String name) {
        String fields = "";
        EntityType<?> entityType = entityManager.getMetamodel().getEntities().stream()
                .filter(entity -> entity.getJavaType().isAnnotationPresent(Metadata.class)
                        && entity.getJavaType().getAnnotation(Metadata.class).value().equals(name))
                .findFirst().orElse(null);
        if (entityType != null) {
            // Class clazz = entityType.getJavaType();
            fields = Stream.of(entityType.getJavaType().getDeclaredFields()).map(Field::getName)
                    .collect(Collectors.joining(", "));
        }

        return fields;
    }
}