package com.nvoip.market.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.AddressRequest;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.common.PhoneRequest;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceDifferentialPricingRequest;
import com.mercadopago.client.preference.PreferenceFreeMethodRequest;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodRequest;
import com.mercadopago.client.preference.PreferencePaymentMethodsRequest;
import com.mercadopago.client.preference.PreferencePaymentTypeRequest;
import com.mercadopago.client.preference.PreferenceReceiverAddressRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.client.preference.PreferenceShipmentsRequest;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.exceptions.MPApiException;

@Service
public class PaymentService {

    public void processPayment() {
        
        MercadoPagoConfig.setAccessToken("TEST-7123191147819459-033119-ee94285f916d3b36ab3863ed284bd65b-1107604901");
        PreferenceClient client = new PreferenceClient();
        
        PreferenceItemRequest itemRequest =
            PreferenceItemRequest.builder()
                .id("1234")
                .title("Peidante")
                .description("Dummy description")
                .pictureUrl("https://www.myapp.com/myimage.jpg")
                .categoryId("car_electronics")
                .quantity(1)
                .currencyId("BRL")
                .unitPrice(new BigDecimal("250"))
                .build();
        
        List<PreferenceItemRequest> items = new ArrayList<>();
        items.add(itemRequest);
    
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
            System.out.println(preference.getInitPoint());
        } catch (MPException | MPApiException e) {
            e.printStackTrace();
        }
    }
}
