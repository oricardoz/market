package com.nvoip.Market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvoip.Market.dto.PurchaseCreateDTO;
import com.nvoip.Market.service.PurchaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {
    
    private final PurchaseService purchaseService;

    @PostMapping
    public void create(@RequestBody PurchaseCreateDTO request) {
        purchaseService.create(request);
    }


}
