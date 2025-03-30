package com.nvoip.market.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvoip.market.domain.Stock;
import com.nvoip.market.dto.AddStockRequestDTO;
import com.nvoip.market.service.StockService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> addStock(@Valid @RequestBody AddStockRequestDTO request) {
        Stock stock = stockService.addStock(request);
        return ResponseEntity.created(URI.create("/stock/" + stock.getId()))
            .body(stock);
    }

}
