package com.nvoip.market.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.nvoip.market.domain.Purchase;
import com.nvoip.market.domain.PurchaseItem;
import com.nvoip.market.domain.User;
import com.nvoip.market.domain.enums.PurchaseStatus;
import com.nvoip.market.dto.AllPurchaseDTO;
import com.nvoip.market.dto.UserCreateDTO;
import com.nvoip.market.exception.UserNotFoundException;
import com.nvoip.market.repository.PurchaseItemRepository;
import com.nvoip.market.repository.PurchaseRepository;
import com.nvoip.market.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PurchaseRepository purchaseRepository;

    private final PurchaseItemRepository purchaseItemRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User create(UserCreateDTO userCreateDTO) {
        return userRepository.save(userCreateDTO.toUser());
    }

    public List<AllPurchaseDTO> findAllPurchases(Long id) {

        userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        List<Purchase> purchases = purchaseRepository.findByUserId(id).orElse(null);

        List<AllPurchaseDTO> allPurchases = purchases.stream()
            .map(purchase -> {
                List<PurchaseItem> items = purchaseItemRepository.findByPurchaseId(purchase.getId());
                return new AllPurchaseDTO(items, purchase.getStatus(), purchase.getPurchaseDate());
            })
            .collect(Collectors.toList());

        return allPurchases;
    }

    public List<AllPurchaseDTO> findAllPurchasesByStatus(Long id, PurchaseStatus status) {
        return findAllPurchases(id).stream()
            .filter(purchase -> purchase.status().equals(status))
            .collect(Collectors.toList());
    }

}
