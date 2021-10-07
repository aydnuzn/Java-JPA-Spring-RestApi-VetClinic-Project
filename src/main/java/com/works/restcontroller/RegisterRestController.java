package com.works.restcontroller;


import com.works.dto.rest.RegisterRestDto;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.works.properties.RegisterChangeInterlayer;
import com.works.properties.RegisterInterlayer;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/register")
@Api(value = "Register Rest Controller", authorizations = {@Authorization(value = "basicAuth")})

public class RegisterRestController {

    final RegisterRestDto registerRestDto;

    public RegisterRestController(RegisterRestDto registerRestDto) {
        this.registerRestDto = registerRestDto;
    }

    @ApiOperation(value = "Kullanıcı Listeleme Servisi")
    @GetMapping("/getAllList")
    public Map<REnum, Object> getAllList() {
        return  registerRestDto.getAllList();
    }

    @ApiOperation(value = "Rol Ekleme Servisi")
    @PostMapping("/add")
    public Map<REnum, Object> register(@Valid @ModelAttribute("registerInterlayer") RegisterInterlayer registerInterlayer, BindingResult bindingResult, @RequestPart("user_file") MultipartFile file) {
        return registerRestDto.register(registerInterlayer,bindingResult,file);
    }

    @ApiOperation(value = "Rol Değiştirme Servisi")
    @PostMapping("/change")
    public  Map<REnum, Object> registerChange(@Valid @RequestBody RegisterChangeInterlayer registerChangeInterlayer, BindingResult bindingResult) {
        return registerRestDto.registerChange(registerChangeInterlayer,bindingResult);
    }

    @ApiOperation(value = "Tüm Rolleri Listeleme Servisi")
    @GetMapping("/getAllListRoles")
    public Map<REnum, Object> getAllListRoles() {
        return registerRestDto.getAllListRoles();
    }

}