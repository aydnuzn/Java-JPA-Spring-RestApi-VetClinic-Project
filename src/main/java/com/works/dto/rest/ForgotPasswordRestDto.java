package com.works.dto.rest;

import com.works.entities.security.ForgotPasswordUser;
import com.works.entities.security.User;
import com.works.repositories._jpa.ForgotPasswordUserRepository;
import com.works.repositories._jpa.UserRepository;
import com.works.services.UserService;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ForgotPasswordRestDto {
    final UserRepository userRepository;
    final ForgotPasswordUserRepository forgotPasswordUserRepository;
    final UserService userService;

    public ForgotPasswordRestDto(UserRepository userRepository, ForgotPasswordUserRepository forgotPasswordUserRepository, UserService userService) {
        this.userRepository = userRepository;
        this.forgotPasswordUserRepository = forgotPasswordUserRepository;
        this.userService = userService;
    }

    public Map<Object, Object> forgotpassword(String us_mail) {
        Map<Object, Object> hm = new LinkedHashMap<>();
        Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(us_mail);
        if (optUser.isPresent()) {
            Optional<ForgotPasswordUser> optForgotPasswordUser = forgotPasswordUserRepository.findByForgotMail(optUser.get().getEmail());
            ForgotPasswordUser forgotPasswordUser = null;
            if(!optForgotPasswordUser.isPresent()){
                forgotPasswordUser = new ForgotPasswordUser();
            }else{
                forgotPasswordUser = optForgotPasswordUser.get();
                forgotPasswordUserRepository.deleteById(forgotPasswordUser.getForgot_id());
            }
            String uuid = UUID.randomUUID().toString();
            forgotPasswordUser.setStatus(true);
            forgotPasswordUser.setUs_mail(us_mail);
            forgotPasswordUser.setForgot_id(uuid);
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İşlem başarılı.");
            hm.put("Durum", "Aktif");
            hm.put("Ref number", uuid);
            hm.put("Email", us_mail);
            forgotPasswordUserRepository.save(forgotPasswordUser);
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "Mail bulunamadı.");
        }
        return hm;
    }

    public Map<REnum, Object> change(String mail, String ref, String newpass) {
        Map<REnum, Object> hm = new LinkedHashMap<>();

        Optional<ForgotPasswordUser> optForgot = forgotPasswordUserRepository.findById(ref);
        Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(mail);
        if (optForgot.isPresent() && optForgot.get().getUs_mail().equals(mail) && optForgot.get().getStatus().equals(true) && optUser.isPresent()) {
            optUser.get().setPassword(userService.encoder().encode(newpass));
            userRepository.saveAndFlush(optUser.get());

            optForgot.get().setStatus(false);
            forgotPasswordUserRepository.saveAndFlush(optForgot.get());
            hm.put(REnum.STATUS, true);
            hm.put(REnum.MESSAGE, "İşlem Başarılı. Şifre değiştirildi.");
        } else {
            hm.put(REnum.STATUS, false);
            hm.put(REnum.MESSAGE, "İşlem Başarısız.");
        }
        return hm;
    }

}
