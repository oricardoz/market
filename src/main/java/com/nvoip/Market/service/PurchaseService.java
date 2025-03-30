package com.nvoip.Market.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nvoip.Market.domain.Product;
import com.nvoip.Market.domain.Purchase;
import com.nvoip.Market.domain.PurchaseItem;
import com.nvoip.Market.domain.Stock;
import com.nvoip.Market.domain.User;
import com.nvoip.Market.domain.enums.PurchaseStatus;
import com.nvoip.Market.dto.PurchaseCreateDTO;
import com.nvoip.Market.repository.ProductRepository;
import com.nvoip.Market.repository.PurchaseItemRepository;
import com.nvoip.Market.repository.PurchaseRepository;
import com.nvoip.Market.repository.StockRepository;
import com.nvoip.Market.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final PurchaseRepository purchaseRepository;

    private final PurchaseItemRepository purchaseItemRepository;

    @Transactional
    public void create(PurchaseCreateDTO request) {

        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(request.productId())
            .orElseThrow(() -> new RuntimeException("Product not found"));

        Stock stock = stockRepository.findByProductId(product.getId())
            .orElseThrow(() -> new RuntimeException("Stock not found"));

        if (stock.getQuantity() < request.quantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        stock.setQuantity(stock.getQuantity() - request.quantity());
        stockRepository.save(stock);

        Purchase purchase = Purchase.builder()
            .user(user)
            .status(PurchaseStatus.PENDING)
            .purchaseDate(LocalDateTime.now())
            .build();

        purchaseRepository.save(purchase);

        PurchaseItem purchaseItem = PurchaseItem.builder()
            .purchase(purchase)
            .unitPrice(product.getPrice())
            .totalPrice(product.getPrice() * request.quantity())
            .product(product)
            .quantity(request.quantity())
            .build();

        purchaseItemRepository.save(purchaseItem);

    
    }

}
