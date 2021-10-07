package com.works.restcontroller;

import com.works.dto.rest.ChangePasswordRestDto;
import com.works.properties.ChangePasswordInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/changepassword")
@Api(value = "Change Password Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class ChangePasswordRestController {
    final ChangePasswordRestDto changePasswordRestDto;

    public ChangePasswordRestController(ChangePasswordRestDto changePasswordRestDto) {
        this.changePasswordRestDto = changePasswordRestDto;
    }

    @ApiOperation(value = "Kullanıcı Şifre Değiştirme Servisi")
    @PostMapping("/change")
    public Map<REnum, Object> changePasswordPost(@Valid @RequestBody ChangePasswordInterlayer changePasswordInterlayer, BindingResult bindingResult) {
        return changePasswordRestDto.changePasswordPost(changePasswordInterlayer, bindingResult);
    }

}
