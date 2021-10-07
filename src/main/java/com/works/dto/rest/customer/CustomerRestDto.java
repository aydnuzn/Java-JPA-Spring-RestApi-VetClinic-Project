package com.works.dto.rest.customer;

import com.works.entities.Customer;
import com.works.entities.CustomerGroup;
import com.works.entities.constant.address.Address;
import com.works.entities.constant.address.City;
import com.works.entities.constant.address.District;
import com.works.entities.constant.pets.*;
import com.works.properties.CustomerInterlayer;
import com.works.properties.CustomerPetInterlayer;
import com.works.properties.PetListInterlayer;
import com.works.repositories._elastic.CustomerElasticSearchRepository;
import com.works.repositories._jpa.*;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.*;

@Service
public class CustomerRestDto {
    final CityRepository cityRepository;
    final DistrictRepository districtRepository;
    final ColorPetRepository colorPetRepository;
    final TypePetRepository typePetRepository;
    final BreedPetRepository breedPetRepository;
    final CustomerGroupRepository customerGroupRepository;
    final CustomerRepository customerRepository;
    final PetRepository petRepository;
    final AddressRepository addressRepository;
    final JoinPetCustomerRepository joinPetCustomerRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;
    final CustomerElasticSearchRepository customerElasticSearchRepository;

    public CustomerRestDto(CityRepository cityRepository, DistrictRepository districtRepository, ColorPetRepository colorPetRepository, TypePetRepository typePetRepository, BreedPetRepository breedPetRepository, CustomerGroupRepository customerGroupRepository, CustomerRepository customerRepository, PetRepository petRepository, AddressRepository addressRepository, JoinPetCustomerRepository joinPetCustomerRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository, CustomerElasticSearchRepository customerElasticSearchRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.colorPetRepository = colorPetRepository;
        this.typePetRepository = typePetRepository;
        this.breedPetRepository = breedPetRepository;
        this.customerGroupRepository = customerGroupRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.addressRepository = addressRepository;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
        this.customerElasticSearchRepository = customerElasticSearchRepository;
    }

    public Map<REnum, Object> addCustomer(CustomerPetInterlayer obj, BindingResult bindingResults) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<JoinPetCustomer> petCustomerList = new ArrayList<>();
        Customer customer = new Customer();
        if (!bindingResults.hasErrors()) {
            //CUSTOMER NESNESİNİ OLUŞTURMA
            CustomerInterlayer customerDto = obj.getCustomerObj();

            customer.setCu_name(customerDto.getCu_name());
            customer.setCu_surname(customerDto.getCu_surname());
            if (Util.isTel(customerDto.getCu_tel1())) {
                customer.setCu_tel1(customerDto.getCu_tel1());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Tel-1 format hatası.");
                return hm;
            }

            if (!customerDto.getCu_tel2().equals("")) {
                if (Util.isTel(customerDto.getCu_tel2())) {
                    customer.setCu_tel2(customerDto.getCu_tel2());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Tel-2 format hatası.");
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

            if (customerDto.getCu_smsNotice().equals("1")) {
                customer.setCu_smsNotice("1");
            } else if (customerDto.getCu_smsNotice().equals("0")) {
                customer.setCu_smsNotice("0");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Sms format hatası");
                return hm;
            }

            if (customerDto.getCu_mailNotice().equals("1")) {
                customer.setCu_mailNotice("1");
            } else if (customerDto.getCu_mailNotice().equals("0")) {
                customer.setCu_mailNotice("0");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Mail format hatası");
                return hm;
            }

            //Address Class Add
            Address address = new Address();

            //CITY
            Integer city_id = 0;
            if (!customerDto.getCu_cities().equals("Seçim Yapınız")) {
                try {
                    city_id = Integer.parseInt(customerDto.getCu_cities());
                } catch (NumberFormatException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "İl Numarası Casting Error");
                    return hm;
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İl numarası rakam olmalı");
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
                    hm.put(REnum.MESSAGE, "İlçe Numarası Cast Hatası");
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
                hm.put(REnum.MESSAGE, "Veri tabanında " + customerDto.getCu_districts() + " numaralı ilçe bulunamadı.");
                return hm;
            }

            //Address <String>
            address.setCu_address(customerDto.getCu_address());

            //Add Address to DB after add customer
            Address address1 = addressRepository.save(address);
            customer.setAddress(address1);

            //CUSTOMERGROUP
            Integer group_id = 0;
            if (!customerDto.getCu_group().equals("Seçim Yapınız")) {
                try {
                    group_id = Integer.parseInt(customerDto.getCu_group());
                } catch (NumberFormatException e) {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Müşteri grubu numarası Casting Error");
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
                hm.put(REnum.MESSAGE, "Veri tabanında " + customerDto.getCu_group() + " numaralı müşteri grubu bulunamadı.");
                return hm;
            }

            try {
                //Eklenen customer
                customer = customerRepository.save(customer);
            } catch (DataIntegrityViolationException e) {
                //email ve tc unique hatası
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında aynı mail - tc kimlik numarası mevcut.");
                return hm;
            }


            //PET LİSTESİ OLUŞTURMA
            for (PetListInterlayer item : obj.getPetList()) {
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
                        hm.put(REnum.MESSAGE, "Pet Color Cast Hatası!");
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
                        hm.put(REnum.MESSAGE, "Pet Type Cast Hatası");
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Tip Seçilmedi");
                    return hm;
                }
                System.out.println("type_id : " + type_id);
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
                        hm.put(REnum.MESSAGE, "Pet Breed Cast Hatası");
                        return hm;
                    }
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Irk Seçilmedi");
                    return hm;
                }
                System.out.println("breed_id : " + breed_id);
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
                joinTypeBreedPet = joinTypeBreedPetRepository.save(joinTypeBreedPet);
                pet.setJoinTypeBreedPet(joinTypeBreedPet);

                pet = petRepository.save(pet);
                JoinPetCustomer joinPetCustomer = new JoinPetCustomer();
                joinPetCustomer.setCustomer(customer);
                joinPetCustomer.setPet(pet);
                joinPetCustomerRepository.save(joinPetCustomer);
                petCustomerList.add(joinPetCustomer);
            }
            //----------------------------------------------------------------------------------------------------------
            //CUSTOMER BAŞARIYLA EKLENMİŞ VARSA DA PETLERİ EKLENMİŞ
            //ELASTIC SEARCH VERİ TABANINA EKLENME İŞLEMİ BURADA GERÇEKLEŞİYOR
            com.works.models.elasticsearch.Customer customerElastic = new com.works.models.elasticsearch.Customer();
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
            //----------------------------------------------------------------------------------------------------------

        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Girilen değerlerde hata(lar) mevcut. (Validasyon)");
            hm.put(REnum.ERROR, Util.errors(bindingResults));
            return hm;
        }
        hm.put(REnum.STATUS, true);
        hm.put(REnum.MESSAGE, "İşlem Başarılı!");
        hm.put(REnum.COUNT, "Pet Sayısı: " + petCustomerList.size() + " Eklenen Müşteri Sayısı: 1");
        if (petCustomerList.size() > 0) {
            hm.put(REnum.RESULT, petCustomerList);
        } else {
            hm.put(REnum.RESULT, customer);
        }
        return hm;
    }

}
