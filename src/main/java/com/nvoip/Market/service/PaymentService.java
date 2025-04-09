package com.nvoip.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceDifferentialPricingRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;

import com.nvoip.market.config.MercadoPagoProperties;
import com.nvoip.market.domain.Purchase;
import com.nvoip.market.domain.PurchaseItem;
import com.nvoip.market.domain.User;
import com.nvoip.market.repository.PurchaseItemRepository;
import com.nvoip.market.repository.PurchaseRepository;
import com.nvoip.market.repository.UserRepository;
import com.nvoip.market.exception.PurchaseNotFoundException;
import com.nvoip.market.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final UserRepository userRepository;
    private final MercadoPagoProperties mercadoPagoProperties;

    private List<PreferenceItemRequest> createPreferenceItems(List<PurchaseItem> purchaseItems) {
        List<PreferenceItemRequest> items = new ArrayList<>();
        
        for (PurchaseItem purchaseItem : purchaseItems) {
            PreferenceItemRequest itemRequest =
                PreferenceItemRequest.builder()
                    .id(purchaseItem.getProduct().getId().toString())
                    .title(purchaseItem.getProduct().getName())
                    .description(purchaseItem.getProduct().getDescription())
                    .pictureUrl("https://www.myapp.com/myimage.jpg") 
                    .categoryId("general") 
                    .quantity(purchaseItem.getQuantity())
                    .currencyId("BRL")
                    .unitPrice(new BigDecimal(purchaseItem.getUnitPrice()))
                    .build();
            
            items.add(itemRequest);
        }
        
        return items;
    }

    public String processPayment(Long purchaseId, Long userId) {
    
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        
        Purchase purchase = purchaseRepository.findById(purchaseId)
            .orElseThrow(() -> new PurchaseNotFoundException("Compra não encontrada"));
        
        if (!purchase.getUser().getId().equals(userId)) {
            throw new RuntimeException("Esta compra não pertence ao usuário informado");
        }

        List<PurchaseItem> purchaseItems = purchaseItemRepository.findByPurchaseId(purchaseId);
        
        if (purchaseItems.isEmpty()) {
            throw new RuntimeException("Nenhum item encontrado para esta compra");
        }
        
        MercadoPagoConfig.setAccessToken(mercadoPagoProperties.getAccessToken());
        PreferenceClient client = new PreferenceClient();
        
        List<PreferenceItemRequest> items = createPreferenceItems(purchaseItems);
    
        PreferenceRequest preferenceRequest = PreferenceRequest.builder()
            .backUrls(
                PreferenceBackUrlsRequest.builder()
                    .success("https://test.com/success")
                    .failure("https://test.com/failure")
                    .pending("https://test.com/pending")
                    .build())
            .differentialPricing(
                PreferenceDifferentialPricingRequest.builder()
                    .id(1L)
                    .build())
            .expires(false)
            .items(items)
            .marketplaceFee(new BigDecimal("0"))
            .build();
           
        try {
            Preference preference = client.create(preferenceRequest);
            return preference.getInitPoint();
        } catch (MPException | MPApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}
