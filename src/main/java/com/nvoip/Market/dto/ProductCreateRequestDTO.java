package com.nvoip.Market.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateRequestDTO(
    @NotBlank(message = "Name is required") 
    String name, 
                                      
    @NotBlank(message = "Description is required") 
    String description,
                                      
    @NotNull(message = "Price is required")                             
    @Positive(message = "Price must be positive")                              
    Double price
    
) {

}
