package com.works.restcontroller;

import com.works.dto.rest.CustomerGroupRestDto;
import com.works.entities.CustomerGroup;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/customergroup")
@Api(value = "CustomerGroup Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class CustomerGroupRestController {

    final CustomerGroupRestDto customerGroupRestDto;

    public CustomerGroupRestController(CustomerGroupRestDto customerGroupDto) {
        this.customerGroupRestDto = customerGroupDto;
    }

    @ApiOperation(value = "Müşteri Grubu Getirme Servisi")
    @GetMapping("/getAllList")
    public Map<REnum, Object> getAllCategory() {
        return customerGroupRestDto.getAllCustomerGroup();
    }

    @ApiOperation(value = "Müşteri Grubu Ekleme Servisi")
    @PostMapping("/add")
    public Map<REnum, Object> customerGroupInsert(@RequestBody @Valid CustomerGroup customerGroup, BindingResult bindingResult) {
        return customerGroupRestDto.customerGroupInsert(customerGroup, bindingResult);
    }

    @ApiOperation(value = "Müşteri Grubu Güncelleme Servisi")
    @PutMapping("/update")
    public Map<REnum, Object> customerGroupUpdate(@RequestBody @Valid CustomerGroup customerGroup, BindingResult bindingResult) {
        return customerGroupRestDto.customerGroupUpdate(customerGroup, bindingResult);
    }

    @ApiOperation(value = "Müşteri Grubu Silme Servisi")
    @DeleteMapping("/delete/{stIndex}")
    public Map<REnum, Object> deleteCustomerGroup(@PathVariable String stIndex) {
        return customerGroupRestDto.deleteCustomerGroup(stIndex);
    }

    @ApiOperation(value = "Müşteri Grubu Arama Servisi")
    @GetMapping("/search/{strSearch}")
    public Map<REnum, Object> getCustomerGroupSearch(@PathVariable String strSearch) {
        return customerGroupRestDto.getCustomerGroupSearch(strSearch);
    }

}
