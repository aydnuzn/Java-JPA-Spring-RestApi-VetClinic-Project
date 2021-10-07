package com.works.restcontroller.customer;

import com.works.dto.rest.customer.CustomerRestDto;
import com.works.properties.CustomerPetInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/rest/customer")
@Api(value = "Customer Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class CustomerRestController {

    final CustomerRestDto customerRestDto;

    public CustomerRestController(CustomerRestDto customerRestDto) {
        this.customerRestDto = customerRestDto;
    }

    @ApiOperation(value = "Müşteri Ekleme Servisi")
    @PostMapping("/add")
    public Map<REnum, Object> addCustomer(@RequestBody @Valid CustomerPetInterlayer obj, BindingResult bindingResults) {
        return customerRestDto.addCustomer(obj, bindingResults);
    }

}