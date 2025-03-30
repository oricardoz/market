package com.nvoip.market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
    Optional<Stock> findByProductId(Long productId);

}
