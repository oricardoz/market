package com.nvoip.market.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nvoip.market.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
