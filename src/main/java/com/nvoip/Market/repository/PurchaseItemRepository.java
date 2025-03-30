package com.nvoip.market.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

    List<PurchaseItem> findByPurchaseId(Long id);

}
