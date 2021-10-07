package com.works.restcontroller;

import com.works.dto.rest.SupplierRestDto;
import com.works.entities.Supplier;
import com.works.utils.REnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/supplier")
@Api(value = "Supplier Rest Controller", authorizations = {@Authorization(value = "basicAuth")})

public class SupplierRestController {

    final SupplierRestDto supplierRestDto;

    public SupplierRestController(SupplierRestDto supplierDto) {
        this.supplierRestDto = supplierDto;
    }

    @ApiOperation(value = "Tedarikçi Listesi")
    @GetMapping("/getAllList")
    public Map<REnum, Object> getAllSupplier() {
        return supplierRestDto.getAllSupplier();
    }

    @ApiOperation(value = "Tedarikçi Ekle")
    @PostMapping("/add")
    public Map<REnum, Object> supplierInsert(@RequestBody @Valid Supplier supplier, BindingResult bindingResult) {
        return supplierRestDto.supplierInsert(supplier, bindingResult);
    }

    @ApiOperation(value = "Tedarikçiyi Güncelle")
    @PutMapping("/update")
    public Map<REnum, Object> supplierUpdate(@RequestBody @Valid Supplier supplier, BindingResult bindingResult) {
        return supplierRestDto.supplierUpdate(supplier, bindingResult);
    }

    @ApiOperation(value = "Tedarikçi Silme")
    @DeleteMapping("/delete/{stIndex}")
    public Map<REnum, Object> deleteSupplier(@PathVariable String stIndex) {
        return supplierRestDto.deleteSupplier(stIndex);
    }

    @ApiOperation(value = "Tedarikçi Arama")
    @GetMapping("/search/{strSearch}")
    public Map<REnum, Object> getSupplierSearch(@PathVariable String strSearch) {
        return supplierRestDto.getSupplierSearch(strSearch);
    }

}