package com.works.restcontroller.customer;

import com.works.dto.rest.customer.CustomerInfoRestDto;
import com.works.properties.UpdatePetInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/rest/customerinfo")
@Api(value = "Customer Info Rest Controller",authorizations = {@Authorization(value = "basicAuth")})
public class CustomerInfoRestController {

    final CustomerInfoRestDto customerInfoRestDto;

    public CustomerInfoRestController(CustomerInfoRestDto customerInfoRestDto) {
        this.customerInfoRestDto = customerInfoRestDto;
    }

    @ApiOperation(value = "Müşteri Bilgisini Getirme Servisi")
    @GetMapping("/{stCid}")
    public Map<REnum, Object> customerInfo(@PathVariable String stCid) {
        return customerInfoRestDto.customerInfo(stCid);
    }

    @ApiOperation(value = "Hayvan Silme Servisi")
    @DeleteMapping("/deletePet/{index}")
    public Map<REnum, Object> petDelete(@PathVariable String index) {
        return customerInfoRestDto.petDelete(index);
    }

    @ApiOperation(value = "Hayvan Bilgisini Getirme Servisi")
    @GetMapping("/getPetInfo/{index}")
    public Map<REnum, Object> getPetInfo(@PathVariable String index) {
        return customerInfoRestDto.getPetInfo(index);
    }

    @ApiOperation(value = "Hayvan Güncelleme Servisi")
    @PutMapping("/updatePet")
    public Map<REnum, Object> updatePet(@RequestBody @Valid UpdatePetInterlayer updatePetInterlayer, BindingResult bindingResults) {
        return customerInfoRestDto.updatePet(updatePetInterlayer, bindingResults);
    }

}
