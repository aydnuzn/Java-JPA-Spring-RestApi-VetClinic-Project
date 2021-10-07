package com.works.dto.rest;

import com.works.entities.CustomerGroup;
import com.works.repositories._jpa.CustomerGroupRepository;
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
public class CustomerGroupRestDto {

    final CustomerGroupRepository customerGroupRepository;

    public CustomerGroupRestDto(CustomerGroupRepository customerGroupRepository) {
        this.customerGroupRepository = customerGroupRepository;
    }

    public Map<REnum, Object> getAllCustomerGroup() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<CustomerGroup> customerGroups = customerGroupRepository.findAll();
        if (customerGroups.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Müşteri Grupları bulundu");

        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri Grubu bulunamadı");
        }
        hm.put(REnum.COUNT, "Veritabanındaki toplam müşteri grubu sayısı : " + customerGroups.size());
        hm.put(REnum.RESULT, customerGroups);
        return hm;
    }

    public Map<REnum, Object> customerGroupInsert(CustomerGroup customerGroup, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                customerGroupRepository.saveAndFlush(customerGroup);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı. Müşteri Grubu eklendi.");
            } catch (DataIntegrityViolationException ex) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir grup bulunmaktadır.");
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

    public Map<REnum, Object> customerGroupUpdate(CustomerGroup customerGroup, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            if (customerGroup.getCu_gr_id() != null) {
                boolean isValidData = customerGroupRepository.existsById(customerGroup.getCu_gr_id());
                if (isValidData) {
                    try {
                        customerGroupRepository.saveAndFlush(customerGroup);
                        hm.put(REnum.STATUS, true);
                        hm.put(REnum.MESSAGE, "İşlem başarılı. Müşteri Grubu güncellendi.");
                    } catch (DataIntegrityViolationException ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Bu isimde bir grup bulunmaktadır.");
                    } catch (Exception ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Ekleme gerçekleşmedi");
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veritabanında " + customerGroup.getCu_gr_id() + " id sine sahip data yoktur.");
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

    public Map<REnum, Object> deleteCustomerGroup(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidCustomerGroup = false;
        try {
            Integer customerGroupId = Integer.parseInt(stIndex);
            isValidCustomerGroup = customerGroupRepository.existsById(customerGroupId);
            if (isValidCustomerGroup) {
                customerGroupRepository.deleteById(customerGroupId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Müşteri Grubu başarıyla silindi");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen Müşteri Grubu mevcut değil");
            }
        } catch (DataIntegrityViolationException ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız. Silinmek istenen müşteri grubunu kullanan veri bulunmaktadır.");
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri Grup Delete casting hatası");
        }
        hm.put(REnum.RESULT, stIndex);
        return hm;
    }

    public Map<REnum, Object> getCustomerGroupSearch(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<CustomerGroup> customerGroupList = new ArrayList<>();
        try {
            customerGroupList = customerGroupRepository.findByCu_gr_nameContainsAllIgnoreCase(strSearch.trim());
            if (customerGroupList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + customerGroupList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, customerGroupList);
        return hm;
    }

}
