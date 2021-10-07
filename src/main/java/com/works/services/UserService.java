package com.works.services;

import com.works.entities.Logger;
import com.works.entities.security.Role;
import com.works.entities.security.User;
import com.works.repositories._jpa.LogRepository;
import com.works.repositories._jpa.UserRepository;
import com.works.utils.Util;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService extends SimpleUrlLogoutSuccessHandler implements UserDetailsService, LogoutSuccessHandler {
    final UserRepository userRepository;
    final LogRepository logRepository;

    public UserService(UserRepository uRepo, LogRepository logRepository) {
        this.userRepository = uRepo;
        this.logRepository = logRepository;
    }

    // security login
    @Override
    public UserDetails loadUserByUsername(String email) {
        UserDetails userDetails = null;
        Optional<User> oUser = userRepository.findByEmailEqualsAllIgnoreCase(email);
        if (oUser.isPresent()) {
            User us = oUser.get();
            userDetails = new org.springframework.security.core.userdetails.User(
                    us.getEmail(),
                    us.getPassword(),
                    us.isEnabled(),
                    us.isTokenExpired(),
                    true,
                    true,
                    getAuthorities(us.getRoles()));
        } else {
            //Util.log("Kullanıcı adı yada şifre hatalı", this.getClass());
            throw new UsernameNotFoundException("Kullanıcı adı yada şifre hatalı");
        }
        return userDetails;
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRo_name()));
        }
        return authorities;
    }

    public User register(User us) throws AuthenticationException {

        if (!Util.isEmail(us.getEmail())) {
            //Util.log("Bu mail formatı hatalı!", this.getClass());
            throw new AuthenticationException("Bu mail formatı hatalı!");
        }

        Optional<User> uOpt = userRepository.findByEmailEqualsAllIgnoreCase(us.getEmail());
        if (uOpt.isPresent()) {
            //Util.log("Bu kullanıcı daha önce kayıtlı!", this.getClass());
            throw new AuthenticationException("Bu kullanıcı daha önce kayıtlı!");
        }
        us.setPassword(encoder().encode(us.getPassword()));
        return userRepository.save(us);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void info(HttpServletRequest req) throws IOException {
        Logger logger = new Logger();
        String email = "";
        try {
            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            email = aut.getName(); // username
        } catch (Exception e) {

        }

        if (email != null) {
            System.out.println(email);
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        System.out.println("ip " + ip);

        String session = req.getSession().getId();
        System.out.println("session :" + session);

        Optional<User> user = userRepository.findByEmailEqualsAllIgnoreCase(email);
        if (user.isPresent()) {
            logger.setLname(user.get().getUs_name());
            logger.setLsurname(user.get().getUs_surname());
            String roles = "";
            for (Role item : user.get().getRoles()) {
                roles += item.getRo_name() + ", ";
            }
            if (roles.length() > 0) {
                roles = roles.substring(0, roles.length() - 2);
            }
            logger.setLroles(roles);
        }

        logger.setLemail(email);
        logger.setLsessionId(session);
        logger.setLIp(ip);

        logger.setLUrl(req.getRequestURI());
        logger.setLDate(new Date());

        logRepository.save(logger);

        System.out.println(logger);
        //Burada aut nesnesinden kendimiz bilgeler (username) alarak ve zamanıda kendimiz ekleyerek oluşturdum.
        //Ama BaseEntity sınıfını da entity'e extend ederek bu bilgileri alabilirdim.
    }
}
