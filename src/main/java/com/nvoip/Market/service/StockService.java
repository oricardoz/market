package com.nvoip.market.service;

import org.springframework.stereotype.Service;

import com.nvoip.market.domain.Product;
import com.nvoip.market.domain.Stock;
import com.nvoip.market.dto.AddStockRequestDTO;
import com.nvoip.market.repository.ProductRepository;
import com.nvoip.market.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    private final ProductRepository productRepository;

    public void addStock(AddStockRequestDTO request) {

        Product product = productRepository.findById(request.productId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

        Stock stock = stockRepository.findByProductId(request.productId())
            .orElse(new Stock(product));

        stock.setQuantity(stock.getQuantity() + request.quantity());
        stockRepository.save(stock);
    }

}
