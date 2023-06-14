package com.back.end.shoppingapi.service;

import java.time.LocalDate;
import	java.time.LocalDateTime;
import	java.util.List;
import	java.util.Optional;
import	java.util.stream.Collectors;

import com.back.end.java.dto.UserDTO;
import com.back.end.shoppingapi.converter.DTOConverter;
import com.back.end.shoppingapi.dto.ItemDTO;
import com.back.end.shoppingapi.dto.ShopDTO;
import com.back.end.shoppingapi.dto.ShopReportDTO;
import com.back.end.shoppingapi.model.Shop;
import com.back.end.shoppingapi.repository.ReportRepository;
import com.back.end.shoppingapi.repository.ShopRepository;

import	com.back.end.java.dto.ProductDTO;
import	lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import	org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    @Autowired
    private final ShopRepository shopRepository;
    private final ReportRepository reportRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    public List<ShopDTO> getAll(){
        List<Shop> shops = shopRepository.findAll();
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByUser(String userIdentifier){
        List<Shop> shops = shopRepository.findAllByUserIdentifier(userIdentifier);
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO){
        List<Shop> shops = shopRepository.findAllByDateGreaterThan(shopDTO.getDate());
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(long ProductId) {
        Optional<Shop> shop = shopRepository.findById(ProductId);
        if(shop.isPresent()) {
            return ShopDTO.convert(shop.get());
        }
        return null;
    }

    public ShopDTO save(ShopDTO shopDTO, String key) {
        UserDTO userDTO = userService.
                getUserByCpf(shopDTO.getUserIdentifier(), key);
        validateProducts(shopDTO.getItems());

        shopDTO.setTotal(shopDTO.getItems()
                .stream()
                .map(x -> x.getPrice())
                .reduce((float) 0, Float::sum));

        Shop shop = Shop.convert(shopDTO);
        shop.setDate(LocalDateTime.now());

        shop = shopRepository.save(shop);
        return DTOConverter.convert(shop);
    }

    private boolean validateProducts(List<ItemDTO> items) {
        for (ItemDTO item: items){
            ProductDTO productDTO = productService.getProductByIdentifier(item.getProductIdentifier());
            if(productDTO == null){
                return false;
            }
            item.setPrice(productDTO.getPreco());
        }
        return true;
    }



    public List<ShopDTO> getShopsByFilter(
            LocalDate dataInicio,
            LocalDate dataFim,
            Float valorMinimo){

        List<Shop> shops = reportRepository.getShopByFilters(dataInicio,dataFim,valorMinimo);
        return shops
                .stream()
                .map(ShopDTO::convert)
                .collect(Collectors.toList());
    }

    public ShopReportDTO getReportByDate(
            LocalDate dataInicio,
            LocalDate dataFim){

        return reportRepository.getReportByDate(dataInicio, dataFim);
    }
}
