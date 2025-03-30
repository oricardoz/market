package com.nvoip.market.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<List<Purchase>> findByUserId(Long id);

}
