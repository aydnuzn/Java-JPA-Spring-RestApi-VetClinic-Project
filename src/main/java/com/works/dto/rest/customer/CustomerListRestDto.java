package com.works.dto.rest.customer;

import com.works.entities.Customer;
import com.works.entities.CustomerGroup;
import com.works.entities.constant.address.Address;
import com.works.entities.constant.address.City;
import com.works.entities.constant.address.District;
import com.works.entities.constant.pets.*;
import com.works.properties.AddPetInterlayer;
import com.works.properties.CustomerInterlayer;
import com.works.repositories._elastic.CustomerElasticSearchRepository;
import com.works.repositories._jpa.*;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class CustomerListRestDto {
    final CustomerRepository customerRepository;
    final TypePetRepository typePetRepository;
    final ColorPetRepository colorPetRepository;
    final CityRepository cityRepository;
    final DistrictRepository districtRepository;
    final BreedPetRepository breedPetRepository;
    final PetRepository petRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;
    final JoinPetCustomerRepository joinPetCustomerRepository;
    final AddressRepository addressRepository;
    final CustomerGroupRepository customerGroupRepository;
    final CustomerElasticSearchRepository customerElasticSearchRepository;

    public CustomerListRestDto(CustomerRepository customerRepository, TypePetRepository typePetRepository, ColorPetRepository colorPetRepository, CityRepository cityRepository, DistrictRepository districtRepository, BreedPetRepository breedPetRepository, PetRepository petRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository, JoinPetCustomerRepository joinPetCustomerRepository, AddressRepository addressRepository, CustomerGroupRepository customerGroupRepository, CustomerElasticSearchRepository customerElasticSearchRepository) {
        this.customerRepository = customerRepository;
        this.typePetRepository = typePetRepository;
        this.colorPetRepository = colorPetRepository;
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.breedPetRepository = breedPetRepository;
        this.petRepository = petRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
        this.addressRepository = addressRepository;
        this.customerGroupRepository = customerGroupRepository;
        this.customerElasticSearchRepository = customerElasticSearchRepository;
    }

    public Map<REnum, Object> getCustomersPageable(String scCount) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Integer count = 0;
        try {
            count = Integer.parseInt(scCount);
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri Casting Hatası: " + e);
            return hm;
        }
        Pageable pageable = null;
        try {
            pageable = PageRequest.of(count, Util.pageSize);
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Sayfa numarası negatif olamaz: " + e);
            return hm;
        }

        List<Customer> customerList = customerRepository.findByOrderByCu_idAsc(pageable);
        long totalCount = customerRepository.count();
        if (customerList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Müşteriler bulundu.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri bulunamadı.");
        }
        hm.put(REnum.COUNT, "Veri tabanındaki toplam müşteri sayısı: " + totalCount);
        Integer c = 10;
        if ((count + 1) * 10 >= totalCount) {
            c = Math.toIntExact(totalCount - c * count);
            if (c < 0) {
                c = 0;
            }
        }

        hm.put(REnum.COUNTOFPAGE, "Sayfa Numarası -> " + count + " Bu sayfadaki sonuç sayısı -> " + c);
        hm.put(REnum.RESULT, customerList);
        return hm;
    }

    public Map<REnum, Object> showCustomer(String index) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Integer customerId = Integer.parseInt(index);
            Optional<Customer> optCustomer = customerRepository.findById(customerId);
            if (optCustomer.isPresent()) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.RESULT, optCustomer.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + customerId + " numaralı müşteri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri idsi rakamlardan oluşmalıdır: " + ex);
        }
        return hm;
    }

    public Map<REnum, Object> deleteCustomer(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        Integer cid = 0;
        try {
            cid = Integer.parseInt(stIndex);
            Optional<Customer> optCustomer = customerRepository.findById(cid);
            if (optCustomer.isPresent()) {
                try {
                    customerRepository.deleteById(cid);
                    //ElasticSearch Delete ->
                    customerElasticSearchRepository.deleteById(cid.toString());
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "Silindi.");
                    hm.put(REnum.RESULT, optCustomer.get());
                } catch (DataIntegrityViolationException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Silinme işlemi iptal edildi. Bağlı tablolar silinemez. (Foreign Key): " + e);
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + cid + " numaralı müşteri bulunamadı.");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Müşteri idsi rakamlardan oluşmalıdır: " + e);
        }
        return hm;
    }

    public Map<REnum, Object> getCustomerSearch(String strSearch) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            List<Customer> customerList = customerRepository.searchCustomer(strSearch.trim());
            if (customerList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Sonuç başarıyla bulundu.");
                hm.put(REnum.COUNT, "Veri tabanında " + customerList.size() + " adet sonuç bulundu.");
                hm.put(REnum.RESULT, customerList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Veri tabanı hatası.: " + ex);
        }
        return hm;
    }

    public Map<REnum, Object> findCustomer(String strSearch, String pageNumber) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Page<com.works.models.elasticsearch.Customer> pageCustomer = customerElasticSearchRepository.findCustomer(strSearch, PageRequest.of(Integer.parseInt(pageNumber.trim()), Util.pageSize));
            List<Customer> customerList = new ArrayList<>();
            pageCustomer.getContent().forEach(item -> {
                customerList.add(customerRepository.findById(Integer.parseInt(item.getId())).get());
            });
            if (customerList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Arama başarılı");
                hm.put(REnum.COUNTOFPAGE, customerList.size());
                hm.put(REnum.RESULT, customerList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Aranan değer bulunamadı");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "strSearch tam sayı olmalı");
        }
        return hm;
    }


    public Map<REnum, Object> addPet(AddPetInterlayer item, BindingResult bindingResults) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResults.hasErrors()) {
            try {
                Pet pet = new Pet();

                pet.setPet_name(item.getName());
                pet.setPet_chipNumber(item.getChipNumber());
                pet.setPet_earTag(item.getEarTag());

                pet.setPet_bornDate(item.getBornDate());

                if (item.getNeutering().equals("true")) {
                    pet.setPet_neutering(true);
                } else {
                    pet.setPet_neutering(false);
                }

                if (item.getGender().equals("true")) {
                    pet.setPet_gender(true);
                } else {
                    pet.setPet_gender(false);
                }

                //Color
                Integer color_id = 0;
                if (!item.getColor().equals("0")) {
                    try {
                        color_id = Integer.parseInt(item.getColor());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Color Cast Hatası!: " + e);
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Renk Seçilmedi.");
                    return hm;
                }

                Optional<ColorPet> optColor_pet = colorPetRepository.findById(color_id);
                if (optColor_pet.isPresent()) {
                    pet.setColorPet(optColor_pet.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri tabanında" + item.getColor() + " numaralı renk bulunamadı.");
                    return hm;
                }

                //Irk Tür Nesnesi oluşturma
                JoinTypeBreedPet joinTypeBreedPet = new JoinTypeBreedPet();

                //Type
                Integer type_id = 0;
                if (!item.getType().equals("0")) {
                    try {
                        type_id = Integer.parseInt(item.getType());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Type Cast Hatası: " + e);
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Tip Seçilmedi");
                    return hm;
                }

                Optional<TypePet> optType = typePetRepository.findById(type_id);
                if (optType.isPresent()) {
                    joinTypeBreedPet.setTypePet(optType.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri tabanında" + item.getType() + " numaralı tür bulunamadı.");
                    return hm;
                }

                //Breed
                Integer breed_id = 0;
                if (!item.getBreed().equals("0")) {
                    try {
                        breed_id = Integer.parseInt(item.getBreed());
                    } catch (NumberFormatException e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Pet Breed Cast Hatası: " + e);
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Irk Seçilmedi");
                    return hm;
                }

                Optional<BreedPet> optBreed = breedPetRepository.findById(breed_id);
                if (optBreed.isPresent()) {
                    if (optBreed.get().getType_pet_id() != optType.get().getTy_id()) {
                        //Girilen Irk numarası Türe ait mi?
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE, "Girilen Irk, girilen türe ait değil.");
                        return hm;
                    }
                    joinTypeBreedPet.setBreedPet(optBreed.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri tabanında" + item.getBreed() + " numaralı ırk bulunamadı.");
                    return hm;
                }

                JoinPetCustomer joinPetCustomer = new JoinPetCustomer();
                Optional<Customer> customer = customerRepository.findById(item.getCu_id());
                if (customer.isPresent()) {
                    joinPetCustomer.setCustomer(customer.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri tabanında" + item.getCu_id() + " numaralı müşteri bulunamadı.");
                    return hm;
                }

                joinTypeBreedPet = joinTypeBreedPetRepository.save(joinTypeBreedPet);
                pet.setJoinTypeBreedPet(joinTypeBreedPet);
                pet = petRepository.save(pet);
                joinPetCustomer.setPet(pet);
                joinPetCustomer = joinPetCustomerRepository.save(joinPetCustomer);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı.");
                hm.put(REnum.RESULT, joinPetCustomer);
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanı hata meydana geldi: " + e);
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Girilen değerlerde hata(lar) mevcut. (Validasyon)");
            hm.put(REnum.ERROR, Util.errors(bindingResults));
        }
        return hm;
    }

    public Map<REnum, Object> updateCustomer(CustomerInterlayer customerDto, BindingResult bindingResults, String customer_id) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<JoinPetCustomer> petCustomerList = new ArrayList<>();
        Customer customer = new Customer();
        Integer cid = 0;
        try {
            cid = Integer.parseInt(customer_id);
            Optional<Customer> optCustomer = customerRepository.findById(cid);
            if (optCustomer.isPresent()) {
                customer.setCu_id(cid);
            } else {
                //Müşteri yok. Update iptal edilecek.
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında " + cid + " numaralı güncelleme yapılmak istenen müşteri bulunamadı.");
                return hm;
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "customer_id rakamlardan oluşmalıdır!");
            return hm;
        }

        if (!bindingResults.hasErrors()) {
            customer.setCu_name(customerDto.getCu_name());
            customer.setCu_surname(customerDto.getCu_surname());
            if (Util.isTel(customerDto.getCu_tel1())) {
                customer.setCu_tel1(customerDto.getCu_tel1());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Telefon1 format hatası.");
                return hm;
            }

            if (!customerDto.getCu_tel2().equals("")) {
                if (Util.isTel(customerDto.getCu_tel2())) {
                    customer.setCu_tel2(customerDto.getCu_tel2());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Telefon2 format hatası.");
                    return hm;
                }
            }

            if (Util.isEmail(customerDto.getCu_mail())) {
                customer.setCu_mail(customerDto.getCu_mail());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Email format hatası.");
                return hm;
            }

            customer.setCu_taxname(customerDto.getCu_taxname());
            customer.setCu_note(customerDto.getCu_note());
            customer.setCu_tcnumber(customerDto.getCu_tcnumber());
            customer.setCu_rateOfDiscount(customerDto.getCu_rateOfDiscount());
            customer.setCu_smsNotice(customerDto.getCu_smsNotice());
            customer.setCu_mailNotice(customerDto.getCu_mailNotice());

            //Address Class Add
            Address address = new Address();

            //CITY
            Integer city_id = 0;
            if (!customerDto.getCu_cities().equals("Seçim Yapınız")) {
                try {
                    city_id = Integer.parseInt(customerDto.getCu_cities());
                } catch (NumberFormatException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "İl numarası Cast Hatası");
                    return hm;
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İl numarası rakamlardan oluşmalıdır.");
                return hm;
            }
            Optional<City> optCity = cityRepository.findById(city_id);
            if (optCity.isPresent()) {
                address.setCity(optCity.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + customerDto.getCu_cities() + " numaralı il bulunamadı.");
                return hm;
            }

            //DISTRICT
            Integer district_id = 0;
            if (!customerDto.getCu_districts().equals("Seçim Yapınız")) {
                try {
                    district_id = Integer.parseInt(customerDto.getCu_districts());
                } catch (NumberFormatException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "İlçe numarası Cast Hatası");
                    return hm;
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İlçe numarası rakamlardan oluşmalıdır.");
                return hm;
            }
            Optional<District> optDistrict = districtRepository.findById(district_id);
            if (optDistrict.isPresent()) {
                if (optCity.get().getCid() != optDistrict.get().getCid()) {
                    //Girilen İlçe numarası İle ait mi?
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Girilen İlçe, girilen İle ait değil.");
                    return hm;
                }
                address.setDistrict(optDistrict.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + customerDto.getCu_districts() + " numaralı ilçe bulunamadı.");
                return hm;
            }

            //Address <String>
            address.setCu_address(customerDto.getCu_address());
            Integer address_id = 0;
            try {
                address_id = customerRepository.findByAddressId(cid);
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + cid + " numaralı il-ilçe ara tablo bilgisi bulunamadı.");
                return hm;
            }
            address.setAddress_id(address_id);
            //Add Address to DB after add customer
            Address address1 = addressRepository.saveAndFlush(address);
            customer.setAddress(address1);

            //CUSTOMERGROUP
            Integer group_id = 0;
            if (!customerDto.getCu_group().equals("Seçim Yapınız")) {
                try {
                    group_id = Integer.parseInt(customerDto.getCu_group());
                } catch (NumberFormatException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Müşteri grubu numarası Cast Hatası");
                    return hm;
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Müşteri grup numarası rakamlardan oluşmalıdır.");
                return hm;
            }
            Optional<CustomerGroup> optGroup = customerGroupRepository.findById(group_id);
            if (optGroup.isPresent()) {
                customer.setCustomerGroup(optGroup.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + customerDto.getCu_group() + " numaralı müşteri grubu bulunamadı.");
                return hm;
            }
            try {
                //Eklenen customer
                customer = customerRepository.saveAndFlush(customer);
                //Elastic Search Güncelleme
                Optional<com.works.models.elasticsearch.Customer> optCustomerModel = customerElasticSearchRepository.findById(customer.getCu_id().toString());
                if (optCustomerModel.isPresent()) {
                    customerElasticSearchRepository.deleteById(customer.getCu_id().toString());
                    com.works.models.elasticsearch.Customer customerElastic = optCustomerModel.get();
                    customerElastic.setId(customer.getCu_id().toString());
                    customerElastic.setName(customer.getCu_name());
                    customerElastic.setSurname(customer.getCu_surname());
                    customerElastic.setTel1(customer.getCu_tel1());
                    customerElastic.setMail(customer.getCu_mail());
                    customerElastic.setTcnumber(customer.getCu_tcnumber());
                    customerElastic.setGroup(customer.getCustomerGroup().getCu_gr_name());
                    customerElastic.setCity(customer.getAddress().getCity().getCname());
                    customerElastic.setDistrict(customer.getAddress().getDistrict().getDname());
                    customerElasticSearchRepository.save(customerElastic);
                } else {
                    System.err.println("Elastic Search Veri Tabanında Mevcut değil.");
                }
                //------------------------------------------------------------------------------------------------------
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem Başarılı!");
                hm.put(REnum.RESULT, customer);
                return hm;
            } catch (DataIntegrityViolationException e) {
                //email ve tc unique hatası
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında aynı mail - tc kimlik numarası mevcut.");
                return hm;
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Girilen değerlerde hata(lar) mevcut. (Validasyon)");
            hm.put(REnum.ERROR, Util.errors(bindingResults));
            return hm;
        }
    }
}
