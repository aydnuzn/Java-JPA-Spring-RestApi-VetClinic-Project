package com.works.dto.rest;

import com.works.entities.security.User;
import com.works.properties.ChangePasswordInterlayer;
import com.works.repositories._jpa.UserRepository;
import com.works.services.UserService;
import com.works.utils.REnum;
import com.works.utils.Util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ChangePasswordRestDto {
    final UserRepository userRepository;
    final UserService userService;

    public ChangePasswordRestDto(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Map<REnum, Object> changePasswordPost(ChangePasswordInterlayer changePasswordInterlayer, BindingResult bindingResult) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        if (!bindingResult.hasErrors()) {
            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username
            Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(email);
            if (optUser.isPresent()) {
                if (changePasswordInterlayer.getNewpassword().equals(changePasswordInterlayer.getNewpasswordr())) {
                    optUser.get().setPassword(userService.encoder().encode(changePasswordInterlayer.getNewpassword()));
                    userRepository.saveAndFlush(optUser.get());
                    hm.put(REnum.STATUS, true);
                    hm.put(REnum.MESSAGE, "İşlem başarılı.");
                } else {
                    hm.put(REnum.STATUS, false);
                    hm.put(REnum.MESSAGE, "Yeni girilen şifreler birbirinden farklı.");
                }
            } else {
                hm.put(REnum.STATUS, false);
                hm.put(REnum.MESSAGE, "Veri tabanında mail bulunamadı.");
            }
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Girilen değerlerde hata(lar) mevcut. (Validasyon)");
            hm.put(REnum.ERROR, Util.errors(bindingResult));
        }
        return hm;
    }

}
