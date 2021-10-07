package com.works.dto.rest;

import com.works.entities.constant.address.City;
import com.works.entities.constant.address.District;
import com.works.entities.constant.pets.BreedPet;
import com.works.entities.constant.pets.ColorPet;
import com.works.entities.constant.pets.TypePet;
import com.works.models.redis.CitySession;
import com.works.models.redis.DistrictSession;
import com.works.repositories._jpa.*;
import com.works.repositories._redis.CitySessionRepository;
import com.works.repositories._redis.DistrictSessionRepository;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConstantRestDto {
    final CityRepository cityRepository;
    final DistrictRepository districtRepository;
    final ColorPetRepository colorPetRepository;
    final TypePetRepository typePetRepository;
    final BreedPetRepository breedPetRepository;
    final CitySessionRepository citySessionRepository;
    final DistrictSessionRepository districtSessionRepository;

    public ConstantRestDto(CityRepository cityRepository, DistrictRepository districtRepository, ColorPetRepository colorPetRepository, TypePetRepository typePetRepository, BreedPetRepository breedPetRepository, CitySessionRepository citySessionRepository, DistrictSessionRepository districtSessionRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.colorPetRepository = colorPetRepository;
        this.typePetRepository = typePetRepository;
        this.breedPetRepository = breedPetRepository;
        this.citySessionRepository = citySessionRepository;
        this.districtSessionRepository = districtSessionRepository;
    }

    public Map<REnum, Object> getCities() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<City> cityList = cityRepository.findAll();
        if (cityList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İller bulundu.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İl bulunamadı.");
        }
        hm.put(REnum.COUNT, cityList.size());
        hm.put(REnum.RESULT, cityList);
        return hm;
    }

    public Map<REnum, Object> addCitiesRedis() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        citySessionRepository.deleteAll();
        List<City> cityList = cityRepository.findAll();
        try {
            cityList.forEach(item -> {
                CitySession citySession = new CitySession();
                citySession.setId(item.getCid().toString());
                citySession.setCityname(item.getCname());
                citySessionRepository.save(citySession);
            });
            if (cityList.size() == 81) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem Başarılı");
                hm.put(REnum.COUNT,citySessionRepository.count());
                hm.put(REnum.RESULT, cityList);
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında eksik bilgi mevcut.");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem Başarısız.");
            hm.put(REnum.ERROR, "Hata oluştu => " + e);
        }
        return hm;
    }

    public Map<REnum, Object> getCitiesRedis() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<CitySession> citySessionList = new ArrayList<>();
        citySessionList = (List<CitySession>) citySessionRepository.findAll();
        if (citySessionList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İller bulundu.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İl bulunamadı");
        }
        hm.put(REnum.COUNT, citySessionList.size());
        hm.put(REnum.RESULT, citySessionList);
        return hm;
    }

    public Map<REnum, Object> getXDistricts(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<District> districtList = new ArrayList<>();
        try {
            int did = Integer.parseInt(stIndex);
            districtList = districtRepository.getXDistricts(did);
            if (districtList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İlçeler bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + did + " numaralı İlçe bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İlçe bulunamadı. - Integer ifade girilmesi gerekir.");
        }
        hm.put(REnum.COUNT, districtList.size());
        hm.put(REnum.RESULT, districtList);
        return hm;
    }

    public Map<REnum, Object> addDistrictsRedis() {
        Map<REnum, Object> hm = new LinkedHashMap<>();

        List<District> districtList = districtRepository.findAll();
        try {
            districtList.forEach(item -> {
                DistrictSession districtSession = new DistrictSession();
                districtSession.setId(item.getDid().toString());
                districtSession.setDistrictname(item.getDname());
                districtSession.setCityid(item.getCid().toString());
                districtSessionRepository.save(districtSession);
            });
            if (districtList.size() == 973) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem Başarılı");
                hm.put(REnum.COUNT, districtSessionRepository.count());
                hm.put(REnum.RESULT, districtSessionRepository.findAll());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında eksik veri");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem Başarısız");
            hm.put(REnum.ERROR, "Hata! " + e);
        }
        return hm;
    }

    public Map<REnum, Object> getXDistrictsRedis(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<DistrictSession> districtSessionList = new ArrayList<>();
        try {
            districtSessionList = districtSessionRepository.findByCityid(stIndex.trim());
            if (districtSessionList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İlçeler bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Id si " + stIndex.trim() + " olan İlçe bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Integer değer giriniz");
        }
        hm.put(REnum.COUNT, districtSessionList.size());
        hm.put(REnum.RESULT, districtSessionList);
        return hm;
    }

    public Map<REnum, Object> getColors() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<ColorPet> colorPetList = colorPetRepository.findAll();
        if (colorPetList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Renkler bulundu.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Renkler bulunamadı.");
        }
        hm.put(REnum.COUNT, colorPetList.size());
        hm.put(REnum.RESULT, colorPetList);
        return hm;
    }

    public Map<REnum, Object> getTypes() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<TypePet> typePetList = typePetRepository.findAll();
        if (typePetList.size() > 0) {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Türler bulundu.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Türler bulunamadı.");
        }
        hm.put(REnum.COUNT, typePetList.size());
        hm.put(REnum.RESULT, typePetList);
        return hm;
    }

    public Map<REnum, Object> getXBreeds(String stIndex) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        List<BreedPet> breedPetList = new ArrayList<>();
        try {
            int did = Integer.parseInt(stIndex);
            breedPetList = breedPetRepository.getXDistricts(did);
            if (breedPetList.size() > 0) {
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "Irklar bulundu.");
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında" + did + " numaralı Irk bulunamadı.");
            }
        } catch (Exception ex) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Irklar bulunamadı. - Integer ifade girilmesi gerekir.");
        }
        hm.put(REnum.COUNT, breedPetList.size());
        hm.put(REnum.RESULT, breedPetList);
        return hm;
    }
}
