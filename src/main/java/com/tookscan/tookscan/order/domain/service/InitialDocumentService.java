package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.order.domain.InitialDocument;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import org.springframework.stereotype.Service;

@Service
public class InitialDocumentService {
    public InitialDocument createInitialDocument(
            String name,
            int pageCount,
            ERecoveryOption recoveryOption,
            Order order,
            PricePolicy pricePolicy
    ) {
        return InitialDocument.builder()
                .name(name)
                .pageCount(pageCount)
                .recoveryOption(recoveryOption)
                .order(order)
                .pricePolicy(pricePolicy)
                .build();
    }
}
