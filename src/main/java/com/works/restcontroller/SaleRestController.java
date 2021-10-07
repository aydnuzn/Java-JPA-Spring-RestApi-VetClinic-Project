package com.works.restcontroller;

import com.works.dto.rest.SaleRestDto;
import com.works.properties.SaleInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/sale")
@Api(value = "Sale Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class SaleRestController {

    final SaleRestDto saleRestDto;

    public SaleRestController(SaleRestDto saleRestDto) {
        this.saleRestDto = saleRestDto;
    }

    @ApiOperation(value = "Yeni Ürün Satış Servisi")
    @PostMapping("/insertSale")
    public Map<REnum, Object> insertSale(@Valid @RequestBody SaleInterlayer saleInterlayer, BindingResult bindingResult) {
        return saleRestDto.insertSale(saleInterlayer, bindingResult);
    }

    @ApiOperation(value = "Satışlar Listeleme Servisi")
    @GetMapping("/getRows")
    public Map<REnum, Object> getRows() {
        return saleRestDto.getRows();
    }

    @ApiOperation(value = "Aramalı Satışlar Listeleme Servisi")
    @GetMapping("/getRowsSearching/{stSearch}")
    public Map<REnum, Object> getRowsSearching(@PathVariable String stSearch) {
        return saleRestDto.getRowsSearching(stSearch);
    }

    @ApiOperation(value = "Satış Silme Servisi")
    @DeleteMapping("/delete/{stPurchaseId}")
    public Map<REnum, Object> delete(@PathVariable String stSaleId) {
        return saleRestDto.delete(stSaleId);
    }

    @ApiOperation(value = "Tüm Müşterileri Listeleme Servisi")
    @GetMapping("/getCustomerList")
    public Map<REnum, Object> getCustomerList() {
        return saleRestDto.getCustomerList();
    }

    @ApiOperation(value = "Tüm Ürünleri Listeleme Servisi")
    @GetMapping("/getProductList")
    public Map<REnum, Object> getProductsCorrect() {
        return saleRestDto.getProductsCorrect();
    }

}