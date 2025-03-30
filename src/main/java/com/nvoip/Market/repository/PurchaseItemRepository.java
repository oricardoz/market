package com.nvoip.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.Market.domain.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Long> {

}
