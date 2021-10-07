package com.works.restcontroller.customer;

import com.works.dto.rest.customer.CustomerListRestDto;
import com.works.properties.AddPetInterlayer;
import com.works.properties.CustomerInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/rest/customerlist")
@Api(value = "Customer List Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class CustomerListRestController {

    final CustomerListRestDto customerListRestDto;

    public CustomerListRestController(CustomerListRestDto customerListRestDto) {
        this.customerListRestDto = customerListRestDto;
    }

    @ApiOperation(value = "Müşterileri Pageable Yöntemiyle Listeleme")
    @GetMapping("/getcustomerspageable/{stCount}")
    public Map<REnum, Object> getCustomersPageable(String scCount) {
        return customerListRestDto.getCustomersPageable(scCount);
    }

    @ApiOperation(value = "Müşteri Listeleme")
    @GetMapping("/showcustomer/{index}")
    public Map<REnum, Object> showCustomer(@PathVariable String index) {
        return customerListRestDto.showCustomer(index);
    }

    @ApiOperation(value = "Müşteri Silme")
    @DeleteMapping("/deletecustomer/{stIndex}")
    public Map<REnum, Object> deleteCustomer(@PathVariable String stIndex) {
        return customerListRestDto.deleteCustomer(stIndex);
    }

    @ApiOperation(value = "Müşteri Arama")
    @GetMapping("/getcustomersearch/{strSearch}")
    public Map<REnum, Object> getCustomerSearch(@PathVariable String strSearch) {
        return customerListRestDto.getCustomerSearch(strSearch);
    }

    @ApiOperation(value = "Müşteri Arama - Elasticsearch")
    @GetMapping("/getcustomersearchwithelasticsearch/{strSearch}/{pageNumber}")
    public Map<REnum, Object> findCustomer(@PathVariable String strSearch, @PathVariable String pageNumber) {
        return customerListRestDto.findCustomer(strSearch, pageNumber);
    }

    @ApiOperation(value = "Pet Ekleme")
    @PostMapping("/addPet")
    public Map<REnum, Object> addPet(@RequestBody @Valid AddPetInterlayer item, BindingResult bindingResults) {
        return customerListRestDto.addPet(item, bindingResults);
    }

    @ApiOperation(value = "Müşteri Güncelleme")
    @PutMapping("/updatecustomer")
    public Map<REnum, Object> updateCustomer(@RequestBody @Valid CustomerInterlayer customerDto, BindingResult bindingResults, @RequestParam("customer_id") String customer_id) {
        return customerListRestDto.updateCustomer(customerDto, bindingResults, customer_id);
    }
}