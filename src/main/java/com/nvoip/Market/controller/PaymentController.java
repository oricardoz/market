package com.nvoip.market.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import com.nvoip.market.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{purchaseId}/{userId}")
    public ResponseEntity<String> processPayment(@PathVariable Long purchaseId, @PathVariable Long userId) {
        String initPoint = paymentService.processPayment(purchaseId, userId);
        return initPoint != null 
            ? ResponseEntity.ok(initPoint)
            : ResponseEntity.badRequest().body("Erro ao processar pagamento");
    }
}