package com.nvoip.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
