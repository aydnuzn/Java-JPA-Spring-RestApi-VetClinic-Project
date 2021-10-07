package com.works.dto.rest;

import com.works.repositories._jpa.UserRepository;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;
import com.works.entities.security.Role;
import com.works.entities.security.User;
import com.works.properties.RegisterChangeInterlayer;
import com.works.properties.RegisterInterlayer;
import com.works.repositories._jpa.RoleRepository;
import com.works.services.UserService;
import com.works.utils.Util;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class RegisterRestDto {


    final UserRepository userRepository;
    final UserService uService;
    final RoleRepository rRepo;

    public RegisterRestDto(UserRepository userRepository, UserService uService, RoleRepository rRepo) {
        this.userRepository = userRepository;
        this.uService = uService;
        this.rRepo = rRepo;
    }

    public Map<REnum, Object> getAllList() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.STATUS, true);
        hm.put(REnum.MESSAGE, "Kullanıcılar başarıyla getirildi.");
        hm.put(REnum.COUNT, userRepository.count());
        hm.put(REnum.RESULT, userRepository.findAll());
        return hm;
    }

    public Map<REnum, Object> getAllListRoles() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.STATUS, true);
        hm.put(REnum.MESSAGE, "Roller başarıyla getirildi.");
        hm.put(REnum.COUNT, rRepo.count());
        hm.put(REnum.RESULT, rRepo.findAll());
        return hm;
    }

    public Map<REnum, Object> register(RegisterInterlayer registerInterlayer, BindingResult bindingResult,MultipartFile file) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                User user = new User();
                user.setUs_name(registerInterlayer.getUser_name());
                user.setUs_surname(registerInterlayer.getUser_surname());
                user.setEmail(registerInterlayer.getUser_email());
                user.setPassword(registerInterlayer.getUser_password());
                user.setUs_tel(registerInterlayer.getUser_tel());
                user.setEnabled(true);
                user.setTokenExpired(true);

                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = "";
                try {//File kısmı validation'da kontrol edilmediği için resim yüklenmemesi durumu kontrolü
                    int length = fileName.lastIndexOf(".");
                    ext = fileName.substring(length, fileName.length());
                    String uui = UUID.randomUUID().toString();
                    fileName = uui + ext;

                    Path path = Paths.get(Util.UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    fileName = "anonim.jpg";
                }

                user.setUser_file(fileName);

                List<Role> roleList = new ArrayList<>();
                for (int i = 0; i < registerInterlayer.getUser_roles().length; i++) {
                    try {
                        Integer roleId = Integer.parseInt(registerInterlayer.getUser_roles()[i]);
                        Optional<Role> optRole = rRepo.findById(roleId);
                        if (optRole.isPresent()) {
                            roleList.add(optRole.get());
                        }else{
                            hm.put(REnum.STATUS, false);
                            hm.put(REnum.MESSAGE, "Veri tabanında " + roleId + " numaraları bir rol bulunamadı.");
                            return hm;
                        }
                    } catch (Exception e) {
                        hm.put(REnum.STATUS, false);
                        hm.put(REnum.MESSAGE,"Dizi içinde String ifade olursa casting hatası");
                        return hm;
                    }
                }
                user.setRoles(roleList);
                uService.register(user);
                hm.put(REnum.STATUS, true);
                hm.put(REnum.MESSAGE, "İşlem başarılı!");
                hm.put(REnum.RESULT, user);
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem başarısız! " + e);
            }
        } else{
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }


    public  Map<REnum, Object> registerChange(RegisterChangeInterlayer registerChangeInterlayer, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            try {
                Integer us_id = Integer.parseInt(registerChangeInterlayer.getChange_user_name());
                Optional<User> optUser = userRepository.findById(us_id);
                List<Role> roleList = new ArrayList<>();
                if (optUser.isPresent()) {
                    for (int i = 0; i < registerChangeInterlayer.getChange_user_roles().length; i++) {
                        try {
                            Integer roleId = Integer.parseInt(registerChangeInterlayer.getChange_user_roles()[i]);
                            Optional<Role> optRole = rRepo.findById(roleId);
                            if (optRole.isPresent()) {
                                roleList.add(optRole.get());
                            }else{
                                hm.put(REnum.STATUS, false);
                                hm.put(REnum.MESSAGE, "Veri tabanında " + roleId + " numaraları bir rol bulunamadı.");
                                return hm;
                            }
                        } catch (Exception e) {
                            hm.put(REnum.STATUS, false);
                            hm.put(REnum.MESSAGE, "İşlem Dizi içinde String ifade olursa casting hatası! " + e);
                            return hm;
                        }
                    }
                    optUser.get().setRoles(roleList);
                    userRepository.saveAndFlush(optUser.get());
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "İşlem Başarılı");
                    hm.put(REnum.RESULT, optUser.get());
                } else{
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Veri tabanında " + us_id + " numaraları bir kullanıcı bulunamadı.");
                    return hm;
                }
            } catch (Exception e) {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "İşlem başarısız! " + e);
            }
        } else{
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Validasyon Hatası. Girilen değerlerde hata/hatalar mevcut.");
            hm.put(REnum.ERROR, Util.errors(bindingResult));

        }
        return hm;
    }
}