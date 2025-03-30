package com.nvoip.Market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.Market.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
