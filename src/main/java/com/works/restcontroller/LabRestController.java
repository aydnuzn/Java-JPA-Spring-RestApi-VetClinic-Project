package com.works.restcontroller;

import com.works.dto.rest.LabRestDto;
import com.works.properties.LabInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.*;

@RequestMapping("/rest/lab")
@RestController
@Api(value = "Lab Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class LabRestController {
    final LabRestDto labRestDto;

    public LabRestController(LabRestDto labRestDto) {
        this.labRestDto = labRestDto;
    }

    @ApiOperation(value = "Tüm Müşterileri Listeleme Servisi")
    @GetMapping("/customerList")
    public Map<REnum, Object> customerList() {
        return labRestDto.customerList();
    }

    @ApiOperation(value = "Tüm Doktorları Listeleme Servisi")
    @GetMapping("/doctorList")
    public Map<REnum, Object> doctorList() {
        return labRestDto.doctorList();
    }

    @ApiOperation(value = "Laboratuvar Sonucu Ekleme Servisi")
    @PostMapping("/insertLab")
    public Map<REnum, Object> insertLab(@Valid @ModelAttribute("labInterlayer") LabInterlayer labInterlayer, BindingResult bindingResult, @RequestPart("lab_file") MultipartFile file) {
        return labRestDto.insertLab(labInterlayer, bindingResult, file);
    }

    @ApiOperation(value = "Laboratuvar Sonucu Listeleme Servisi")
    @GetMapping("/getLabList")
    public Map<REnum, Object> getLabList() {
        return labRestDto.getLabList();
    }

    @ApiOperation(value = "Laboratuvar Sonucu Arama Servisi")
    @GetMapping("/getLabListSearch/{stSearch}")
    public Map<REnum, Object> getLabListSearch(@PathVariable String stSearch) {
        return labRestDto.getLabListSearch(stSearch);
    }

    @ApiOperation(value = "Laboratuvar Sonucu Silme Servisi")
    @DeleteMapping("/delete/{stLabId}")
    public Map<REnum, Object> delete(@PathVariable String stLabId) {
        return labRestDto.delete(stLabId);
    }

}