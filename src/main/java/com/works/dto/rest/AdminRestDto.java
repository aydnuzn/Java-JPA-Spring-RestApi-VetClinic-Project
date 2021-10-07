package com.works.dto.rest;

import com.works.entities.security.User;
import com.works.services.UserService;
import com.works.utils.REnum;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class AdminRestDto {

    final UserService uService;

    public AdminRestDto(UserService uService) {
        this.uService = uService;
    }

    public Map<REnum, Object> logout() {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        hm.put(REnum.STATUS, true);
        hm.put(REnum.MESSAGE, "Çıkış başarılı.");
        return hm;
    }
}
