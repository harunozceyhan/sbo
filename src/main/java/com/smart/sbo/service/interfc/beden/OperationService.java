package com.smart.sbo.service.interfc.beden;

import java.util.Date;

import com.smart.sbo.domain.beden.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by harun on 14.05.2019.
 */
public interface OperationService {
    Page<Operation> getOperationList(String adi, String kodu, Date startOperationDate, Date endOperationDate,
            Pageable pageable);
}
