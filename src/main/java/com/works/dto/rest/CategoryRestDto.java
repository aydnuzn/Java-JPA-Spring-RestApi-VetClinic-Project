package com.works.dto.rest;

import com.works.entities.Category;
import com.works.repositories._jpa.CategoryRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryRestDto {
    final CategoryRepository categoryRepository;

    public CategoryRestDto(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Map<REnum, Object> getAllCategory() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Kategori bulundu");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Kategori bulunamadı");
        }
        hm.put(REnum.COUNT, "Veritabanındaki toplam Kategori sayısı : " + categories.size());
        hm.put(REnum.RESULT, categories);
        return hm;
    }

    public Map<REnum, Object> categoryInsert(Category category, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                categoryRepository.saveAndFlush(category);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı. Kategori eklendi.");
            } catch (DataIntegrityViolationException ex) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir Kategori bulunmaktadır.");
            } catch (Exception ex) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem Başarısız. Ekleme gerçekleşmedi");
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

    public Map<REnum, Object> categoryUpdate(Category category, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            if (category.getCategory_id() != null) {
                boolean isValidData = categoryRepository.existsById(category.getCategory_id());
                if (isValidData) {
                    try {
                        categoryRepository.saveAndFlush(category);
                        hm.put(REnum.STATUS, true);
                        hm.put(REnum.MESSAGE, "İşlem başarılı. Kategori güncellendi.");
                    } catch (DataIntegrityViolationException ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir Kategori bulunmaktadır.");
                    } catch (Exception ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Ekleme gerçekleşmedi");
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veritabanında " + category.getCategory_id() + " id sine sahip data yoktur.");
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Güncellenmek istenen verinin primary key i null olamaz");
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

    public Map<REnum, Object> deleteCategory(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidCustomerGroup = false;
        try {
            Integer customerGroupId = Integer.parseInt(stIndex);
            isValidCustomerGroup = categoryRepository.existsById(customerGroupId);
            if (isValidCustomerGroup) {
                categoryRepository.deleteById(customerGroupId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Kategori başarıyla silindi");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen Kategori mevcut değil");
            }
        } catch (DataIntegrityViolationException ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız. Silinmek istenen Kategoriyi kullanan veri bulunmaktadır.");
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Kategori Delete casting hatası");
        }
        hm.put(REnum.RESULT, stIndex);
        return hm;
    }

    public Map<REnum, Object> getCategorySearch(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Category> categoryList = new ArrayList<>();
        try {
            categoryList = categoryRepository.findByCategory_titleContainsIgnoreCase(strSearch.trim());
            if (categoryList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + categoryList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, categoryList);
        return hm;
    }

}

