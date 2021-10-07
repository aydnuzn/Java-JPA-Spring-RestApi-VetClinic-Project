package com.works.restcontroller;

import com.works.dto.rest.ConstantRestDto;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/rest/constant")
@Api(value = "Constant Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class ConstantRestController {

    final ConstantRestDto constantRestDto;

    public ConstantRestController(ConstantRestDto constantRestDto) {
        this.constantRestDto = constantRestDto;
    }

    @ApiOperation(value = "İlleri Listele")
    @GetMapping("/getCities")
    public Map<REnum, Object> getCities() {
        return constantRestDto.getCities();
    }

    @ApiOperation(value = "İlleri Ekleme - Redis")
    @PostMapping("/addCitiesRedis")
    public Map<REnum, Object> addCitiesRedis() {
        return constantRestDto.addCitiesRedis();
    }

    @ApiOperation(value = "İlleri Listele - Redis")
    @GetMapping("/getCitiesRedis")
    public Map<REnum, Object> getCitiesRedis() {
        return constantRestDto.getCitiesRedis();
    }

    @ApiOperation(value = "İllere Göre İlçeleri Listele")
    @GetMapping("/getXDistricts/{stIndex}")
    public Map<REnum, Object> getXDistricts(@PathVariable String stIndex) {
        return constantRestDto.getXDistricts(stIndex);
    }

    @ApiOperation(value = "İlçe Ekleme - Redis")
    @PostMapping("/addDistrictsRedis")
    public Map<REnum, Object> addDistrictsRedis() {
        return constantRestDto.addDistrictsRedis();
    }

    @ApiOperation(value = "İllere Göre İlçeleri Listele - Redis")
    @GetMapping("/getXDistrictsRedis/{stIndex}")
    public Map<REnum, Object> getXDistrictsRedis(@PathVariable String stIndex) {
        return constantRestDto.getXDistrictsRedis(stIndex);
    }

    @ApiOperation(value = "Pet Renklerini Listele")
    @GetMapping("/getColors/")
    public Map<REnum, Object> getColors() {
        return constantRestDto.getColors();
    }

    @ApiOperation(value = "Pet Türlerini Listele")
    @GetMapping("/getTypes/")
    public Map<REnum, Object> getTypes() {
        return constantRestDto.getTypes();
    }

    @ApiOperation(value = "Seçilen Türe Göre Irkları Listele")
    @GetMapping("/getXBreeds/{stIndex}")
    public Map<REnum, Object> getXBreeds(@PathVariable String stIndex) {
        return constantRestDto.getXBreeds(stIndex);
    }

}
