package com.works.restcontroller;


import com.works.dto.rest.ProductRestDto;
import com.works.properties.ProductInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/product")
@Api(value = "Product Rest Controller", authorizations = {@Authorization(value = "basicAuth")})

public class ProductRestController {

    final ProductRestDto productRestDto;

    public ProductRestController(ProductRestDto productRestDto) {
        this.productRestDto = productRestDto;
    }

    @ApiOperation(value = "Tüm Ürünleri Listeleme Servisi")
    @GetMapping("/getAllList")
    public Map<REnum, Object> getAllProduct() {
        return productRestDto.getAllProduct();
    }

    @ApiOperation(value = "Yeni Ürün Ekleme Servisi")
    @PostMapping("/add")
    public Map<REnum, Object> productInsert(@RequestBody @Valid ProductInterlayer productInterlayer, BindingResult bindingResult) {
        return productRestDto.productInsert(productInterlayer, bindingResult);
    }

    @ApiOperation(value = "Mevcut Ürünü Güncelleme Servisi")
    @PutMapping("/update")
    public Map<REnum, Object> productUpdate(@RequestBody @Valid ProductInterlayer productInterlayer, BindingResult bindingResult, @RequestParam("product_id") Integer product_id ) {
        return productRestDto.productUpdate(productInterlayer, bindingResult, product_id);
    }

    @ApiOperation(value = "Ürün Silme Servisi")
    @DeleteMapping("/delete/{stIndex}")
    public Map<REnum, Object> deleteProduct(@PathVariable String stIndex) {
        return productRestDto.deleteProduct(stIndex);
    }

    @ApiOperation(value = "Ürün Arama Servisi")
    @GetMapping("/search/{strSearch}")
    public Map<REnum, Object> getProductSearch(@PathVariable String strSearch) {
        return productRestDto.getProductSearch(strSearch);
    }

}