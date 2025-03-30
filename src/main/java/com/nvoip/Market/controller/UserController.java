package com.nvoip.market.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nvoip.market.domain.User;
import com.nvoip.market.domain.enums.PurchaseStatus;
import com.nvoip.market.dto.UserCreateDTO;
import com.nvoip.market.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        User user = userService.create(userCreateDTO);
        return ResponseEntity.created(URI.create("/users/" + user.getId()))
            .body(user);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<?> findPurchases(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findAllPurchases(id));
    }

    @GetMapping("/{id}/purchases/{status}")
    public ResponseEntity<?> findPurchasesByStatus(@PathVariable Long id, @PathVariable PurchaseStatus status) {
        return ResponseEntity.ok(userService.findAllPurchasesByStatus(id, status));
    }


}
