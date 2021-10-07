package com.works.restcontroller.security;

import com.works.dto.rest.AdminRestDto;
import com.works.entities.security.User;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest/admin")
@Api(value = "Admin Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class AdminRestController {

    final AdminRestDto adminRestDto;

    public AdminRestController(AdminRestDto adminRestDto) {
        this.adminRestDto = adminRestDto;
    }

    @ApiOperation(value = "Admin Çıkış Yapma Servisi")
    @GetMapping("/logout")
    public Map<REnum, Object> logout() {
        return adminRestDto.logout();
    }

}
