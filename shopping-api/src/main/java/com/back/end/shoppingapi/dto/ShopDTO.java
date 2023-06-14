package com.back.end.shoppingapi.dto;

import java.time.LocalDateTime;
import	java.util.Date;
import	java.util.List;

import com.back.end.shoppingapi.model.Shop;
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

    public static ShopDTO convert (Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());
        return shopDTO;
    }
}
