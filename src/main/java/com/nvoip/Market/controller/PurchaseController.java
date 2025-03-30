package com.nvoip.market.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvoip.market.domain.Purchase;
import com.nvoip.market.dto.PurchaseCreateDTO;
import com.nvoip.market.service.PurchaseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {    
    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> create(@Valid @RequestBody PurchaseCreateDTO request) {
        Purchase purchase = purchaseService.create(request);
        return ResponseEntity.created(URI.create("/purchases/" + purchase.getId()))
            .body(purchase);
    }


}
