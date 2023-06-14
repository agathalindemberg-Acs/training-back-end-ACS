package com.back.end.java.dto;

import java.time.LocalDateTime;
import	java.util.List;

import	jakarta.validation.constraints.NotBlank;
import	jakarta.validation.constraints.NotNull;

import	lombok.AllArgsConstructor;
import	lombok.Getter;
import	lombok.NoArgsConstructor;
import	lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShopDTO {
    @NotBlank
    private String userIdentifier;
    private Float total;
    private LocalDateTime date;
    @NotNull
    private List<ItemDTO> items;
}
