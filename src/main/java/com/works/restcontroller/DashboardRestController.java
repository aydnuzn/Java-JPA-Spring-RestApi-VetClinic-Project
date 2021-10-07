package com.works.restcontroller;

import com.works.dto.rest.DashboardRestDto;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/rest/dashboard")
@Api(value = "Dashboard Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class DashboardRestController {

    final DashboardRestDto dashboardRestDto;

    public DashboardRestController(DashboardRestDto dashboardRestDto) {
        this.dashboardRestDto = dashboardRestDto;
    }

    @ApiOperation(value = "Anasayfa Kart Bilgileri Getirme Servisi")
    @GetMapping("")
    public Map<Object, Object> dashboard() {
        return dashboardRestDto.dashboard();
    }

    @ApiOperation(value = "Haftanın Günleri Müşteri kayıt Listesi")
    @GetMapping("/dayOfWeeks")
    public Map<REnum, Object> getCustomerRegisterDayOfWeeks() {
        return dashboardRestDto.getCustomerRegisterDayOfWeeks();
    }

    @ApiOperation(value = "Pet Cİnslerine Göre Yüzdelik Dagılım Listesi")
    @GetMapping("/petTypeCount")
    public Map<String, Object> getPetTypeCount() {
        return dashboardRestDto.getPetTypeCount();
    }

}