package com.smart.sbo.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.validation.constraints.NotNull;

import com.smart.sbo.annotation.MetaColumn;
import com.smart.sbo.annotation.Metadata;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

    @GetMapping("/{name}")
    public ResponseEntity<Object> metadataName(@PathVariable String name) {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        List<Map<String, Object>> columnList = new ArrayList<>();
        List<Map<String, Object>> tabs = new ArrayList<>();
        EntityType<?> entityType = entityManager.getMetamodel().getEntities().stream()
                .filter(entity -> entity.getJavaType().isAnnotationPresent(Metadata.class)
                        && entity.getJavaType().getAnnotation(Metadata.class).value().equals(name))
                .findFirst().orElse(null);
        if (entityType != null) {
            Metadata classMetadata = entityType.getJavaType().getAnnotation(Metadata.class);
            responseMap.put("value", classMetadata.value());
            responseMap.put("title", classMetadata.title());
            responseMap.put("detailTitleKey", classMetadata.detailTitleKey());
            responseMap.put("baseUrl", classMetadata.baseUrl());
            responseMap.put("getUrl", classMetadata.getUrl());
            responseMap.put("responseKey", classMetadata.responseKey());

            List<Field> fieldList = Arrays.asList(entityType.getJavaType().getDeclaredFields()).stream()
                    .filter(field -> field.isAnnotationPresent(MetaColumn.class)).collect(Collectors.toList());
            fieldList.forEach(field -> {
                Map<String, Object> columnsMap = new HashMap<String, Object>();
                MetaColumn fieldMeta = field.getAnnotation(MetaColumn.class);
                String fieldType = field.getType().getSimpleName();
                if (fieldType.equals("String")) {
                    columnsMap.put("type", fieldMeta.type().equals("") ? "text" : fieldMeta.type());
                    columnsMap.put("formType", fieldMeta.formType().equals("") ? "text" : fieldMeta.formType());
                } else if (fieldType.equals("Date")) {
                    columnsMap.put("type", fieldMeta.type().equals("") ? "text" : fieldMeta.type());
                    columnsMap.put("formType", fieldMeta.formType().equals("") ? "datepicker" : fieldMeta.formType());
                } else if (fieldType.equals("Boolean")) {
                    columnsMap.put("type", fieldMeta.type().equals("") ? "boolean" : fieldMeta.type());
                    columnsMap.put("formType", fieldMeta.formType().equals("") ? "checkbox" : fieldMeta.formType());
                } else if (fieldType.equals("Integer") || fieldType.equals("Double") || fieldType.equals("Float")) {
                    columnsMap.put("type", fieldMeta.type().equals("") ? "number" : fieldMeta.type());
                    columnsMap.put("formType", fieldMeta.formType().equals("") ? "text" : fieldMeta.formType());
                } else { // Object
                    columnsMap.put("type", fieldMeta.type().equals("") ? "object" : fieldMeta.type());
                    columnsMap.put("formType", fieldMeta.formType().equals("") ? "combobox" : fieldMeta.formType());
                    columnsMap.put("metadata", field.getType().getAnnotation(Metadata.class).value());
                }

                if (field.isAnnotationPresent(Length.class)) {
                    Length fieldLengthMeta = field.getAnnotation(Length.class);
                    columnsMap.put("min", fieldLengthMeta.min());
                    columnsMap.put("max", fieldLengthMeta.max());
                } else {
                    columnsMap.put("min", fieldMeta.min());
                    columnsMap.put("max", fieldMeta.max());
                }
                columnsMap.put("required", field.isAnnotationPresent(NotNull.class));
                columnsMap.put("text", fieldMeta.text().equals("") ? field.getName() : fieldMeta.text());
                columnsMap.put("value", fieldMeta.value().equals("") ? field.getName() : fieldMeta.value());
                columnsMap.put("tableValue",
                        fieldMeta.tableValue().equals("") ? field.getName() : fieldMeta.tableValue());
                columnsMap.put("searchKey", fieldMeta.searchKey().equals("") ? field.getName() : fieldMeta.searchKey());
                columnsMap.put("width", fieldMeta.width());
                columnsMap.put("url", fieldMeta.url());
                columnsMap.put("responseKey", fieldMeta.responseKey());
                columnsMap.put("itemText", fieldMeta.itemText());
                columnsMap.put("sortable", fieldMeta.sortable());
                columnsMap.put("showInTable", fieldMeta.showInTable());
                columnsMap.put("searchable", fieldMeta.searchable());

                columnList.add(columnsMap);
            });
            responseMap.put("columns", columnList);
            responseMap.put("tabs", tabs);
        } else {
            throw new ResourceNotFoundException("MetaData Not Found");
        }

        return ResponseEntity.ok(responseMap);
    }
}