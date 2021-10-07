package com.works.dto.rest;

import com.works.entities.Supplier;
import com.works.repositories._jpa.SupplierRepository;
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
public class SupplierRestDto {
    final SupplierRepository supplierRepository;

    public SupplierRestDto(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Map<REnum, Object> getAllSupplier() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Supplier> supplierList = supplierRepository.findAll();
        if (supplierList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Tedarikçi bulundu");

        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Tedarikçi bulunamadı");
        }
        hm.put(REnum.COUNT, "Veritabanındaki toplam Tedarikçi sayısı : " + supplierList.size());
        hm.put(REnum.RESULT, supplierList);
        return hm;
    }

    public Map<REnum, Object> supplierInsert(Supplier supplier, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                supplierRepository.saveAndFlush(supplier);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı. Tedarikçi eklendi.");
            } catch (DataIntegrityViolationException ex) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir Tedarikçi bulunmaktadır.");
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

    public Map<REnum, Object> supplierUpdate(Supplier supplier, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            if (supplier.getSupplier_id() != null) {
                boolean isValidData = supplierRepository.existsById(supplier.getSupplier_id());
                if (isValidData) {
                    try {
                        supplierRepository.saveAndFlush(supplier);
                        hm.put(REnum.STATUS, true);
                        hm.put(REnum.MESSAGE, "İşlem başarılı. Tedarikçi güncellendi.");
                    } catch (DataIntegrityViolationException ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir Tedarikçi bulunmaktadır.");
                    } catch (Exception ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Ekleme gerçekleşmedi");
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veritabanında " + supplier.getSupplier_id() + " id sine sahip data yoktur.");
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

    public Map<REnum, Object> deleteSupplier(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidCustomerGroup = false;
        try {
            Integer customerGroupId = Integer.parseInt(stIndex);
            isValidCustomerGroup = supplierRepository.existsById(customerGroupId);
            if (isValidCustomerGroup) {
                supplierRepository.deleteById(customerGroupId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Tedarikçi başarıyla silindi");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen Tedarikçi mevcut değil");
            }
        } catch (DataIntegrityViolationException ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız. Silinmek istenen Tedarikçiyi kullanan veri bulunmaktadır.");
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Tedarikçi Delete casting hatası");
        }
        hm.put(REnum.RESULT, stIndex);
        return hm;
    }

    public Map<REnum, Object> getSupplierSearch(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Supplier> supplierList = new ArrayList<>();
        try {
            supplierList = supplierRepository.findBySupplier_nameContainsAllIgnoreCase(strSearch.trim());
            if (supplierList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + supplierList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, supplierList);
        return hm;
    }

}
