package com.nvoip.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
