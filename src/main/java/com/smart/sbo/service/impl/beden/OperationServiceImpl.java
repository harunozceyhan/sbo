package com.smart.sbo.service.impl.beden;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.smart.sbo.domain.beden.Operation;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.smart.sbo.repository.OperationRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.smart.sbo.service.interfc.beden.OperationService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    public Page<Operation> getOperationList(String adi, String kodu, Date startOperationDate, Date endOperationDate,
            Pageable pageable) {

        return operationRepository.findAll(new Specification<Operation>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Operation> root, CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("adi"), "%" + adi + "%"),
                        criteriaBuilder.like(root.get("kodu"), "%" + kodu + "%")));
                if (startOperationDate != null) {
                    predicates.add(criteriaBuilder
                            .and(criteriaBuilder.greaterThanOrEqualTo(root.get("operationDate"), startOperationDate)));
                }
                if (endOperationDate != null) {
                    predicates.add(criteriaBuilder
                            .and(criteriaBuilder.lessThanOrEqualTo(root.get("operationDate"), endOperationDate)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        }, pageable);
    }
}