package com.works.restcontroller;

import com.works.dto.rest.ProfileInfoRestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/rest/profile")
@Api(value = "Profile Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class ProfileInfoRestController {

    final ProfileInfoRestDto profileInfoRestDto;

    public ProfileInfoRestController(ProfileInfoRestDto profileInfoRestDto) {
        this.profileInfoRestDto = profileInfoRestDto;
    }

    @ApiOperation(value = "Fotoğraf Getirme Servisi")
    @GetMapping("/getPhoto")
    public Map<String, Object> getPhoto() {
        return profileInfoRestDto.getPhoto();
    }
}