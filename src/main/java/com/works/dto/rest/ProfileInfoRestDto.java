package com.works.dto.rest;

import com.works.entities.security.User;
import com.works.repositories._jpa.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProfileInfoRestDto {
    final UserRepository userRepository;

    public ProfileInfoRestDto(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> getPhoto() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName(); // username

        Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(email);
        Map<String, Object> hm = new LinkedHashMap<>();
        if (optUser.isPresent()) {
            hm.put("profilePhoto", optUser.get().getUser_file());
        }
        return hm;
    }
}
