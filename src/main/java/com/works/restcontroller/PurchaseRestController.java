package com.works.restcontroller;

import com.works.dto.rest.PurchaseRestDto;
import com.works.properties.PurchaseInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/purchase")
@Api(value = "Purchase Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class PurchaseRestController {

    final PurchaseRestDto purchaseRestDto;
    public PurchaseRestController(PurchaseRestDto purchaseRestDto) {
        this.purchaseRestDto = purchaseRestDto;
    }

    @ApiOperation(value = "Satış Ekleme Servisi")
    @PostMapping("/insertPurchase")
    public Map<REnum, Object> insertPurchase(@Valid @RequestBody PurchaseInterlayer purchaseInterlayer, BindingResult bindingResult) {
        return purchaseRestDto.insertPurchase(purchaseInterlayer,bindingResult);
    }

    @ApiOperation(value = "Tedarikçi Listeleme Servisi")
    @GetMapping("/getSupplierList")
    public Map<REnum, Object> getSupplierList() {
        return purchaseRestDto.getSupplierList();
    }

    @ApiOperation(value = "Ürün Listeleme Servisi")
    @GetMapping("/getProductList")
    public Map<REnum, Object> getProductList() {
        return purchaseRestDto.getProductList();
    }

    @ApiOperation(value = "Satış Satır Getirme Servisi")
    @GetMapping("/getRows")
    public Map<REnum, Object> getRows() {
        return purchaseRestDto.getRows();
    }

    @ApiOperation(value = "Satış Arama Servisi")
    @GetMapping("/getRowsSearching/{search}")
    public Map<REnum, Object> getRowsSearching(@PathVariable String search) {
        return purchaseRestDto.getRowsSearching(search);
    }

    @ApiOperation(value = "Satış Silme Servisi")
    @DeleteMapping("/delete/{stPurchaseId}")
    public Map<REnum, Object> delete(@PathVariable String stPurchaseId) {
        return purchaseRestDto.delete(stPurchaseId);
    }


}