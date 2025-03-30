package com.nvoip.Market.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nvoip.Market.domain.Product;
import com.nvoip.Market.dto.ProductCreateRequestDTO;
import com.nvoip.Market.repository.ProductRepository;

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
