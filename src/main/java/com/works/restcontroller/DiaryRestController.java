package com.works.restcontroller;

import com.works.dto.rest.DiaryRestDto;
import com.works.properties.DiaryInterlayer;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/rest/diary")
@Api(value = "Diary Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class DiaryRestController {

    final DiaryRestDto diaryRestDto;

    public DiaryRestController(DiaryRestDto diaryRestDto) {
        this.diaryRestDto = diaryRestDto;
    }

    @ApiOperation(value = "Randevu Ekleme Servisi")
    @PostMapping("/insert")
    public Map<REnum, Object>  addInsert(@Valid @RequestBody DiaryInterlayer diaryInterlayer, BindingResult bindingResult) {
        return diaryRestDto.addInsert(diaryInterlayer, bindingResult);
    }

    @ApiOperation(value = "Randevu Listeleme Servisi")
    @GetMapping("/diaryList")
    public  Map<REnum, Object>getDiaryList() {
        return diaryRestDto.getDiaryList();
    }

    @ApiOperation(value = "Randevu Arama Servisi")
    @GetMapping("/diaryList/{search}")
    public  Map<REnum, Object> getDiaryListSearch(@PathVariable String search) {
        return diaryRestDto.getDiaryListSearch(search);
    }

    @ApiOperation(value = "Randevu Silme Servisi")
    @GetMapping("/delete/{stDiary_id}")
    public  Map<REnum, Object> delete(@PathVariable String stDiary_id) {
       return diaryRestDto.delete(stDiary_id);
    }
}
