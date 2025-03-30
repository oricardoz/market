package com.nvoip.Market.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.Market.domain.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
    
    Optional<Stock> findByProductId(Long productId);

}
