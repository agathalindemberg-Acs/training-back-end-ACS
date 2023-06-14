package com.back.end.shoppingapi.controller;

import java.time.LocalDate;
import	java.util.List;

import com.back.end.shoppingapi.dto.ShopDTO;
import com.back.end.shoppingapi.dto.ShopReportDTO;
import com.back.end.shoppingapi.service.ShopService;
import jakarta.validation.Valid;
import	lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService shopService;

    @GetMapping("/shopping")
    public List<ShopDTO> getShops(){
        return shopService.getAll();
    }

    @GetMapping("/shopping/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable String userIdentifier){
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopping/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO){
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/shopping/{id}")
    public ShopDTO findById(@PathVariable Long id){
        return shopService.findById(id);
    }

    //POST para cadastrar uma nova compra
    @PostMapping("/shopping")
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(
            @RequestHeader(name = "key", required = true) String key,
            @RequestBody ShopDTO shopDTO){
        return shopService.save(shopDTO, key);
    }

    //http://localhost:8082/shopping/search?dataInicio=01/01/2023&dataFim=01/01/2024&valorMinimo=50
    @GetMapping("/shopping/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataInicio,
            @RequestParam(name = "dataFim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim,
            @RequestParam(name = "valorMinimo", required = false)Float valorMinimo){
        return shopService.getShopsByFilter(dataInicio,dataFim,valorMinimo);

    }

    @GetMapping("/shopping/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy")LocalDate dataInicio,
            @RequestParam(name = "dataFim", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim){
        return shopService.getReportByDate(dataInicio, dataFim);
    }

}
