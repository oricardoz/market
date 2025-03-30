package com.nvoip.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.Market.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
