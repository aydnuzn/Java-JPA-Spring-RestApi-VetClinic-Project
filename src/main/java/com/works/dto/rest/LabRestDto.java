package com.works.dto.rest;

import com.works.entities.Lab;
import com.works.entities.constant.pets.JoinPetCustomer;
import com.works.entities.security.Role;
import com.works.entities.security.User;
import com.works.properties.LabInterlayer;
import com.works.repositories._jpa.CustomerRepository;
import com.works.repositories._jpa.JoinPetCustomerRepository;
import com.works.repositories._jpa.LabRepository;
import com.works.repositories._jpa.UserRepository;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class LabRestDto {

    final CustomerRepository customerRepository;
    final UserRepository userRepository;
    final LabRepository labRepository;
    final JoinPetCustomerRepository joinPetCustomerRepository;

    public LabRestDto(CustomerRepository customerRepository, UserRepository userRepository, LabRepository labRepository, JoinPetCustomerRepository joinPetCustomerRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.labRepository = labRepository;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
    }

    public Map<REnum, Object> customerList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Liste başarıyla geri döndü. (Müşteri - pet çifti)");
            hm.put(REnum.COUNT, customerRepository.count());
            hm.put(REnum.RESULT, customerRepository.allCustomerAndPetInfos());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Listeleme sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> doctorList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Liste başarıyla geri döndü.");
            hm.put(REnum.RESULT, userRepository.getUsersForRoleId(2));
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Listeleme sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> insertLab(LabInterlayer labInterlayer, BindingResult bindingResult, MultipartFile file) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            Lab lab = new Lab();

            Integer us_id = labInterlayer.getUs_id();
            Optional<User> obtUser = userRepository.findById(us_id);
            if (obtUser.isPresent()) {
                Boolean feedBack = false;
                for (Role item : obtUser.get().getRoles()) {
                    if (item.getRo_name().equals("ROLE_DOCTOR")) {
                        feedBack = true;
                    }
                }
                if (feedBack) {
                    lab.setUser(obtUser.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Kullanıcı Doctor değildir.");
                    return hm;
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "User bulunamadı.");
                return hm;
            }

            Integer jpt_id = labInterlayer.getJpt_id();
            Optional<JoinPetCustomer> optJoinPetCustomer = joinPetCustomerRepository.findById(jpt_id);
            if (optJoinPetCustomer.isPresent()) {
                lab.setJoinPetCustomer(optJoinPetCustomer.get());
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Pet bulunamadı.");
                return hm;
            }

            Integer lab_type = labInterlayer.getLab_type();
            lab.setLab_type(lab_type);

            String lab_detail = labInterlayer.getLab_detail();
            lab.setLab_detail(lab_detail);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String ext = "";
            try {//File kısmı validation'da kontrol edilmediği için resim yüklenmemesi durumu kontrolü
                int length = fileName.lastIndexOf(".");
                ext = fileName.substring(length, fileName.length());
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Resim yüklenmesi zaruridir.");
                return hm;
            }
            String uui = UUID.randomUUID().toString();
            fileName = uui + ext;
            lab.setLab_file(fileName);
            try {
                Path path = Paths.get(Util.UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                //add database
                labRepository.save(lab);
            } catch (IOException e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.ERROR, "Resim kopyalama işlemi sırasında hata oluştu.");
                return hm;
            }
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İşlem başarılı.");
            return hm;
        }
        hm.put(REnum.STATUS, false);
        hm.put(REnum.MESSAGE, "Girilen değerlerde hata(lar) mevcut. (Validasyon)");
        hm.put(REnum.ERROR, Util.errors(bindingResult));
        return hm;
    }

    public Map<REnum, Object> getLabList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Liste başarıyla geri döndü.");
            hm.put(REnum.COUNT, labRepository.count());
            hm.put(REnum.RESULT, labRepository.labPageRowsData());
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Listeleme sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> getLabListSearch(String search) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "Liste başarıyla geri döndü.");
            hm.put(REnum.RESULT, labRepository.labPageRowsDataSearch(search));
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.ERROR, "Listeleme sırasında bir hata oluştu");
        }
        return hm;
    }

    public Map<REnum, Object> delete(String stLabId) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            Integer labId = Integer.parseInt(stLabId);
            Optional<Lab> optLab = labRepository.findById(labId);
            if (optLab.isPresent()) {
                labRepository.deleteById(labId);
                File file = new File(Util.UPLOAD_DIR + optLab.get().getLab_file());
                if (file.exists()) {
                    file.delete();
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "İşlem başarılı.");
                    hm.put(REnum.RESULT, optLab.get());
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "File oluşturuladı");
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Index Cast Hatası");
            }
        } catch (Exception e) {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Index Cast Hatası");
        }
        return hm;
    }
}