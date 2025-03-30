package com.nvoip.market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nvoip.market.domain.Product;
import com.nvoip.market.dto.ProductCreateRequestDTO;
import com.nvoip.market.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product create(ProductCreateRequestDTO request) {
        return productRepository.save(new Product(request));
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
