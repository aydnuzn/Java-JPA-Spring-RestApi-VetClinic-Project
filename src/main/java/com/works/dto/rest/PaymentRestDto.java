package com.works.dto.rest;

import com.works.entities.Customer;
import com.works.entities.PaymentIn;
import com.works.entities.PaymentOut;
import com.works.entities.projections.AllPayInOutInfo;
import com.works.entities.projections.DebtorCustomerInfo;
import com.works.entities.projections.PayInPayOutInfo;
import com.works.properties.PayInInterlayer;
import com.works.repositories._jpa.CustomerRepository;
import com.works.repositories._jpa.PaymentInRepository;
import com.works.repositories._jpa.PaymentOutRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class PaymentRestDto {

    final PaymentInRepository paymentInRepository;
    final PaymentOutRepository paymentOutRepository;
    final CustomerRepository customerRepository;

    public PaymentRestDto(PaymentInRepository paymentInRepository, PaymentOutRepository paymentOutRepository, CustomerRepository customerRepository) {
        this.paymentInRepository = paymentInRepository;
        this.paymentOutRepository = paymentOutRepository;
        this.customerRepository = customerRepository;
    }

    // Kasa para girişi tümü
    public Map<REnum, Object> getAllPayIn() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentInList = paymentInRepository.getAllPayIn(null);
            if (paymentInList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentInList.size());
                hm.put(REnum.RESULT, paymentInList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentInList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Kasa para girişi son 10 data
    public Map<REnum, Object> getPayInLastTen() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentInList = paymentInRepository.getAllPayInLastTen();
            if (paymentInList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentInList.size());
                hm.put(REnum.RESULT, paymentInList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentInList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Kasa para çıkışı tümü
    public Map<REnum, Object> getAllPayOut() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentOutList = paymentOutRepository.getAllPayOut(null);
            if (paymentOutList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentOutList.size());
                hm.put(REnum.RESULT, paymentOutList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentOutList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Kasa para çıkışı son 10
    public Map<REnum, Object> getPayOutLastTen() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentOutList = paymentOutRepository.getPayOutLastTenInfo();
            if (paymentOutList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentOutList.size());
                hm.put(REnum.RESULT, paymentOutList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentOutList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Kasa para giriş-çıkış tümü
    public Map<REnum, Object> getAllPayInOut() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentInOutList = paymentInRepository.getAllPayInfo();
            if (paymentInOutList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentInOutList.size());
                hm.put(REnum.RESULT, paymentInOutList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentInOutList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Kasa para giriş-çıkış son 10
    public Map<REnum, Object> getPaymentLastTenInfo() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<AllPayInOutInfo> paymentInOutLastTenList = paymentInRepository.getPayLastTenInfo();
            if (paymentInOutLastTenList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı");
                hm.put(REnum.COUNT, paymentInOutLastTenList.size());
                hm.put(REnum.RESULT, paymentInOutLastTenList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı");
                hm.put(REnum.RESULT, paymentInOutLastTenList);
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veritabanı hatası");
        }
        return hm;
    }

    // Borcu olan müşteri id,isim ve borcu
    public Map<REnum, Object> debtorCustomer() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<DebtorCustomerInfo> ls = new ArrayList<>();
        try {
            List<DebtorCustomerInfo> debtorCustomerInfoList = paymentInRepository.debtorCustomerInfo();
            if (debtorCustomerInfoList.size() > 0) {
                for (int i = 0; i < debtorCustomerInfoList.size(); i++) {
                    int totalPrice = Integer.parseInt(String.valueOf(debtorCustomerInfoList.get(i).getTotalPrice()));
                    int totalPayment = Integer.parseInt(String.valueOf(debtorCustomerInfoList.get(i).getTotalPayment()));
                    if (totalPrice > totalPayment) {
                        ls.add(debtorCustomerInfoList.get(i));
                    }
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Borcu olan musteri mevcut değil.");
            }

            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Borcu bulunan müşteriler getirildi");
            hm.put(REnum.COUNT, ls.size());
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Hata meydana geldi");
        }
        hm.put(REnum.RESULT, ls);
        return hm;
    }

    // Kasaya giren ve çıkan toplam para
    public Map<REnum, Object> getPaymentTotalInOutInfo() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Optional<PayInPayOutInfo> payInPayOutInterLayer = paymentOutRepository.totalPayInOut();
        if (payInPayOutInterLayer.isPresent()) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Kasaya giren ve çıkan toplam para miktarı getirildi.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri yok");
        }
        hm.put(REnum.RESULT, payInPayOutInterLayer.get());
        return hm;
    }


    // Kasa para girişi
    public Map<REnum, Object> insertPayIn(PayInInterlayer payInInterlayer, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            PaymentIn paymentIn = new PaymentIn();
            paymentIn.setPin_detail(payInInterlayer.getPin_detail());
            paymentIn.setPin_price(payInInterlayer.getPin_price());
            paymentIn.setPin_operationType(0);  // kasa girişi
            paymentIn.setPin_paymentType(payInInterlayer.getPin_paymentType());

            Optional<Customer> optCustomer = customerRepository.findById(payInInterlayer.getCu_id());
            if (optCustomer.isPresent()) {
                paymentIn.setCustomer(optCustomer.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Seçilen müşteri mevcut değil");
                return hm;
            }
            PaymentIn paymentInSave = paymentInRepository.save(paymentIn);
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İşlem başarılı");
            hm.put(REnum.RESULT, paymentInSave);
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

    // Kasa para çıkışı
    public Map<REnum, Object> insertPayOut(PaymentOut paymentOut, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            paymentOut.setPout_operationType(1);
            // Kasadaki Para miktarı, çekilmek istene miktardan fazlaysa devam edecek
            Optional<PayInPayOutInfo> payInPayOutInterLayer = paymentOutRepository.totalPayInOut();
            if (payInPayOutInterLayer.isPresent()) {
                int totalPayIn = payInPayOutInterLayer.get().getTotalPayIn();
                int totalPayOut = payInPayOutInterLayer.get().getTotalPayOut() == null ? 0 : payInPayOutInterLayer.get().getTotalPayOut();
                if (totalPayIn > totalPayOut) {
                    PaymentOut paymentOutSave = paymentOutRepository.save(paymentOut);
                    Integer pay = paymentOutSave.getPout_price();
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "İşlem Başarılı. Kasadan para çıkışı gerçekleşti. Kasada kalan para : " + (totalPayIn - totalPayOut - pay));
                    hm.put(REnum.RESULT, paymentOutSave);
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Kasada yeteri miktarda para mevcut değil. Kasadaki mevcut para : " + (totalPayIn - totalPayOut));
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Kasaya para girişi yapılmamıştır.");
                return hm;
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

    // Kasa para girişi delete
    public Map<REnum, Object> deletePayIn(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidPay = false;
        try {
            Integer payId = Integer.parseInt(stIndex);
            isValidPay = paymentInRepository.existsById(payId);
            if (isValidPay) {
                paymentInRepository.deleteById(payId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Silindi.");
                hm.put(REnum.RESULT, stIndex);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen veri mevcut değil");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "PaymentOut Delete casting hatası");
        }
        return hm;
    }

    // Kasa para çıkışı delete
    public Map<REnum, Object> deletePayOut(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidPay = false;
        try {
            Integer payId = Integer.parseInt(stIndex);
            isValidPay = paymentOutRepository.existsById(payId);
            if (isValidPay) {
                paymentOutRepository.deleteById(payId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Silindi.");
                hm.put(REnum.RESULT, stIndex);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen veri mevcut değil");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "PaymentOut Delete casting hatası");
        }
        return hm;
    }

    // Kasa girişi arama
    public Map<REnum, Object> getPayInSearchList(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<AllPayInOutInfo> paymentInList = new ArrayList<>();
        try {
            paymentInList = paymentInRepository.getAllPayInSearchList(strSearch.trim());
            if (paymentInList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + paymentInList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, paymentInList);
        return hm;
    }

    // Kasa çıkışı arama
    public Map<REnum, Object> getPayOutSearchList(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<AllPayInOutInfo> paymentOutList = new ArrayList<>();
        try {
            paymentOutList = paymentOutRepository.getAllPayOutSearchList(strSearch.trim());
            if (paymentOutList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + paymentOutList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, paymentOutList);
        return hm;
    }

    // Kasa tümü arama
    public Map<REnum, Object> getPayInOutSearchList(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<AllPayInOutInfo> paymentInOutList = new ArrayList<>();
        try {
            paymentInOutList = paymentInRepository.getPaySearchList(strSearch.trim());
            if (paymentInOutList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + paymentInOutList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, paymentInOutList);
        return hm;
    }
}