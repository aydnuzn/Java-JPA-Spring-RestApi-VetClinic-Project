package com.works.restcontroller;

import com.works.dto.rest.CategoryRestDto;
import com.works.entities.Category;
import com.works.utils.REnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rest/category")
@Api(value = "Category Rest Controller", authorizations = {@Authorization(value = "basicAuth")})
public class CategoryRestController {

    final CategoryRestDto categoryRestDto;

    public CategoryRestController(CategoryRestDto categoryDto) {
        this.categoryRestDto = categoryDto;
    }

    @ApiOperation(value = "Tüm Kategorileri Listeleme Servisi")
    @GetMapping("/getAllList")
    public Map<REnum, Object> getAllCategory() {
        return categoryRestDto.getAllCategory();
    }

    @ApiOperation(value = "Yeni Kategori Ekleme Servisi")
    @PostMapping("/add")
    public Map<REnum, Object> categoryInsert(@RequestBody @Valid Category category, BindingResult bindingResult) {
        return categoryRestDto.categoryInsert(category, bindingResult);
    }

    @ApiOperation(value = "Kategori Güncelleme Servisi")
    @PutMapping("/update")
    public Map<REnum, Object> categoryUpdate(@RequestBody @Valid Category category, BindingResult bindingResult) {
        return categoryRestDto.categoryUpdate(category, bindingResult);
    }

    @ApiOperation(value = "Kategori Silme Servisi")
    @DeleteMapping("/delete/{stIndex}")
    public Map<REnum, Object> deleteCategory(@PathVariable String stIndex) {
        return categoryRestDto.deleteCategory(stIndex);
    }

    @ApiOperation(value = "Kategori Arama Servisi")
    @GetMapping("/search/{strSearch}")
    public Map<REnum, Object> getCategorySearch(@PathVariable String strSearch) {
        return categoryRestDto.getCategorySearch(strSearch);
    }


}
