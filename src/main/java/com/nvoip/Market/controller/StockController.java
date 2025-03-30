package com.nvoip.Market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvoip.Market.dto.AddStockRequestDTO;
import com.nvoip.Market.service.StockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/add")
    public void addStock(@RequestBody AddStockRequestDTO request) {
        stockService.addStock(request);
    }

}
