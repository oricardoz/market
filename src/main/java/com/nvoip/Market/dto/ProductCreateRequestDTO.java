package com.nvoip.market.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequestDTO(
    @NotBlank(message = "Name is required") 
    String name, 
                                      
    @NotBlank(message = "Description is required") 
    String description,
                                      
    @NotNull(message = "Price is required")                             
    @Min(value = 0, message = "Price must be positive")                              
    Double price
    
) {

}
