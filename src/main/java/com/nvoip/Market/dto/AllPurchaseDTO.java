package com.nvoip.market.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.nvoip.market.domain.PurchaseItem;
import com.nvoip.market.domain.enums.PurchaseStatus;

public record AllPurchaseDTO(
    List<PurchaseItem> purchaseItems,
    PurchaseStatus status,
    LocalDateTime purchaseDate
) {

}
