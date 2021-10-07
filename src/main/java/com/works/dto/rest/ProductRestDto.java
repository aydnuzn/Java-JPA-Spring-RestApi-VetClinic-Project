package com.works.dto.rest;

import com.works.entities.Category;
import com.works.entities.Product;
import com.works.properties.ProductInterlayer;
import com.works.repositories._jpa.CategoryRepository;
import com.works.repositories._jpa.ProductRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class ProductRestDto {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductRestDto(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    // Tüm ürünleri getir
    public Map<REnum, Object> getAllProduct() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Product> productList = productRepository.findAll();
        if (productList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Ürünler bulundu");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Ürünler bulunamadı");
        }
        hm.put(REnum.COUNT, "Veritabanındaki toplam ürün sayısı : " + productList.size());
        hm.put(REnum.RESULT, productList);
        return hm;
    }

    // Ürün ekle
    public Map<REnum, Object> productInsert(ProductInterlayer productInterlayer, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                Product product = new Product();
                product.setProduct_name(productInterlayer.getProduct_name());
                product.setProduct_unit(productInterlayer.getProduct_unit());
                product.setProduct_kdv(productInterlayer.getProduct_kdv());
                product.setProduct_alis(productInterlayer.getProduct_alis());
                product.setProduct_satis(productInterlayer.getProduct_satis());
                product.setProduct_stokMiktari(productInterlayer.getProduct_stokMiktari());
                product.setProduct_statu(productInterlayer.getProduct_statu());
                Optional<Category> optionalCategory = categoryRepository.findById(productInterlayer.getCategory());
                if (optionalCategory.isPresent()) {
                    product.setCategory(optionalCategory.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Seçilen kategori mevcut değil");
                    return hm;
                }

                productRepository.saveAndFlush(product);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı. Ürün eklendi.");
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

    //Ürün güncelle
    public Map<REnum, Object> productUpdate(ProductInterlayer productInterlayer, BindingResult bindingResult, Integer product_id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            if (product_id != null) {
                boolean isValidData = productRepository.existsById(product_id);
                if (isValidData) {
                    try {
                        Product product = new Product();
                        product.setProduct_id(product_id);
                        product.setProduct_name(productInterlayer.getProduct_name());
                        product.setProduct_unit(productInterlayer.getProduct_unit());
                        product.setProduct_kdv(productInterlayer.getProduct_kdv());
                        product.setProduct_alis(productInterlayer.getProduct_alis());
                        product.setProduct_satis(productInterlayer.getProduct_satis());
                        product.setProduct_stokMiktari(productInterlayer.getProduct_stokMiktari());
                        product.setProduct_statu(productInterlayer.getProduct_statu());
                        Optional<Category> optionalCategory = categoryRepository.findById(productInterlayer.getCategory());
                        if (optionalCategory.isPresent()) {
                            product.setCategory(optionalCategory.get());
                        } else {
                            hm.put(REnum.STATUS, false);
                            hm.put(REnum.MESSAGE, "Seçilen kategori mevcut değil");
                            return hm;
                        }

                        productRepository.saveAndFlush(product);
                        hm.put(REnum.STATUS, true);
                        hm.put(REnum.MESSAGE, "İşlem başarılı. Ürün Güncellendi.");
                    } catch (Exception ex) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "İşlem Başarısız. Ekleme gerçekleşmedi");
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veritabanında " + product_id + " id sine sahip data yoktur.");
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


    //Ürün Silme
    public Map<REnum, Object> deleteProduct(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Boolean isValidProduct = false;
        try {
            Integer productId = Integer.parseInt(stIndex);
            isValidProduct = productRepository.existsById(productId);
            if (isValidProduct) {
                productRepository.deleteById(productId);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Ürün başarıyla silindi");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Silinmek istenen Ürün mevcut değil");
            }
        } catch (DataIntegrityViolationException ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem başarısız. Silinmek istenen Ürünü kullanan veri bulunmaktadır.");
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Ürün Delete casting hatası");
        }
        hm.put(REnum.RESULT, stIndex);
        return hm;
    }

    // Ürün arama
    public Map<REnum, Object> getProductSearch(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<Product> productList = new ArrayList<>();
        try {
            productList = productRepository.findByProduct_nameContainsAllIgnoreCase(strSearch.trim());
            if (productList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + productList.size() + " adet sonuç bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.");
            return hm;
        }
        hm.put(REnum.RESULT, productList);
        return hm;
    }

}
