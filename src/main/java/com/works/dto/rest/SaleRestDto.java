package com.works.dto.rest;

import com.works.entities.Customer;
import com.works.entities.Product;
import com.works.entities.Sale;
import com.works.entities.projections.SaleInfo;
import com.works.properties.SaleInterlayer;
import com.works.repositories._jpa.CustomerRepository;
import com.works.repositories._jpa.ProductRepository;
import com.works.repositories._jpa.SaleRepository;
import com.works.repositories._jpa.StoreRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class SaleRestDto {
    final CustomerRepository cRepo;
    final SaleRepository sRepo;
    final ProductRepository pRepo;
    final StoreRepository storeRepository;

    public SaleRestDto(CustomerRepository cRepo, SaleRepository sRepo, ProductRepository pRepo, StoreRepository storeRepository) {
        this.cRepo = cRepo;
        this.sRepo = sRepo;
        this.pRepo = pRepo;
        this.storeRepository = storeRepository;
    }

    public Map<REnum, Object> insertSale(SaleInterlayer saleInterlayer, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            Sale sale = new Sale();
            sale.setSale_code(UUID.randomUUID().toString());
            sale.setSale_detail(saleInterlayer.getPNote());
            sale.setSale_number(saleInterlayer.getPAmount());
            sale.setSale_type(saleInterlayer.getPPaymentType());

            Optional<Product> optProduct = pRepo.findById(saleInterlayer.getPid());
            if (optProduct.isPresent()) {
                optProduct.get().setProduct_stokMiktari(optProduct.get().getProduct_stokMiktari() - sale.getSale_number());
                sale.setProduct(optProduct.get());
                pRepo.saveAndFlush(optProduct.get());

                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Başarılı");
                hm.put(REnum.RESULT, optProduct);

            }
            Optional<Customer> optCustomer = cRepo.findById(saleInterlayer.getCid());
            if (optCustomer.isPresent()) {
                sale.setCustomer(optCustomer.get());
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Başarılı");
                hm.put(REnum.RESULT, optCustomer);

            }
            if (optProduct.isPresent() && optCustomer.isPresent()) {
                //TUTAR HESAPLAMA
                Integer kdvTutarı = (optProduct.get().getProduct_satis() * optProduct.get().getProduct_kdv()) / 100;
                Integer birimFiyatKDVli = optProduct.get().getProduct_satis() + kdvTutarı;
                Integer araFiyat = birimFiyatKDVli * saleInterlayer.getPAmount();
                Integer indirimOranı = 0;
                try {
                    indirimOranı = Integer.parseInt(optCustomer.get().getCu_rateOfDiscount());
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "Başarılı");
                    hm.put(REnum.RESULT, indirimOranı);

                } catch (Exception e) {

                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.ERROR, "Hata oluştu!");

                }
                Integer total = araFiyat - (araFiyat * indirimOranı) / 100;
                sale.setSale_total(total);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Başarılı");
                hm.put(REnum.RESULT, total);
            }

            sRepo.save(sale);
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Başarılı");
            hm.put(REnum.RESULT, sale);
            return hm;
        }else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

    public Map<REnum, Object> getCustomerList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Müşteri(ler) başarıyla bulundu.");
            hm.put(REnum.COUNT, cRepo.count());
            hm.put(REnum.RESULT, cRepo.findAll());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Müşteri bulma sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> getProductsCorrect() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Ürün(ler) başarıyla bulundu.");
            hm.put(REnum.RESULT, pRepo.findAll());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Ürün bulma sırasında bir hata oluştu");
        }
        return null;
    }

    public Map<REnum, Object> getRows() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "getRows işlemi başarıyla gerçekleşti");
            hm.put(REnum.COUNT, sRepo.count());
            hm.put(REnum.RESULT, sRepo.getRows());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "getRows işlemi sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> getRowsSearching(String stSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<SaleInfo> saleInfos = sRepo.getRowsSearching(stSearch.trim());
            if (saleInfos.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Arama işlemi başarıyla gerçekleşti");
                hm.put(REnum.COUNT, "Veri tabanında " + saleInfos.size() + " adet sonuç bulundu.");
                hm.put(REnum.RESULT, sRepo.getRowsSearching(stSearch));
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Arama sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> delete(String stSaleId) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Integer saleId = Integer.parseInt(stSaleId);
            Optional<Sale> optSale = sRepo.findById(saleId);
            if (optSale.isPresent()) {
                optSale.get().getProduct().setProduct_stokMiktari(optSale.get().getProduct().getProduct_stokMiktari() + optSale.get().getSale_number());
                pRepo.saveAndFlush(optSale.get().getProduct());
                sRepo.deleteById(saleId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Başarıyla silindi");
                hm.put(REnum.RESULT, optSale);
                return hm;
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silme işlemi gerçekleşemedi.");
            }

        } catch (Exception e) {
            hm.put(REnum.ERROR, "SaleController Error");
        }
        return hm;
    }


}
