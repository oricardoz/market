package com.nvoip.market.dto;

import com.nvoip.market.domain.User;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
    @NotBlank
    String name,
    @NotBlank
    String numbersip,
    @NotBlank
    String password
) {
    public User toUser() {
        return new User(name, numbersip, password); 
    }

}
