package com.nvoip.market.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.nvoip.market.domain.Product;
import com.nvoip.market.domain.Purchase;
import com.nvoip.market.domain.PurchaseItem;
import com.nvoip.market.domain.Stock;
import com.nvoip.market.domain.User;
import com.nvoip.market.domain.enums.PurchaseStatus;
import com.nvoip.market.dto.PurchaseCreateDTO;
import com.nvoip.market.exception.InsufficientStockException;
import com.nvoip.market.exception.ProductNotFoundException;
import com.nvoip.market.exception.StockNotFoundException;
import com.nvoip.market.exception.UserNotFoundException;
import com.nvoip.market.repository.ProductRepository;
import com.nvoip.market.repository.PurchaseItemRepository;
import com.nvoip.market.repository.PurchaseRepository;
import com.nvoip.market.repository.StockRepository;
import com.nvoip.market.repository.UserRepository;

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
    public Purchase create(PurchaseCreateDTO request) {

        User user = userRepository.findById(request.userId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        Product product = productRepository.findById(request.productId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        Stock stock = stockRepository.findByProductId(product.getId())
            .orElseThrow(() -> new StockNotFoundException("Stock not found"));

        if (stock.getQuantity() < request.quantity()) {
            throw new InsufficientStockException("Insufficient stock");
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

        return purchase;
    }

}
