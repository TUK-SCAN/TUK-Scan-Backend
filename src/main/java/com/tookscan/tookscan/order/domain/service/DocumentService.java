package com.tookscan.tookscan.order.domain.service;

import com.tookscan.tookscan.order.domain.Document;
import com.tookscan.tookscan.order.domain.Order;
import com.tookscan.tookscan.order.domain.PricePolicy;
import com.tookscan.tookscan.order.domain.type.ERecoveryOption;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    public Document createDocument(
            String name,
            int pageCount,
            ERecoveryOption recoveryOption,
            Order order,
            PricePolicy pricePolicy
    ) {
        return Document.builder()
                .name(name)
                .pageCount(pageCount)
                .recoveryOption(recoveryOption)
                .order(order)
                .pricePolicy(pricePolicy)
                .build();
    }

}
