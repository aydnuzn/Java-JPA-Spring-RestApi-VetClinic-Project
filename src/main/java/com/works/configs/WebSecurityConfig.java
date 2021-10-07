package com.works.configs;

import com.works.repositories._jpa.UserFollowInRepository;
import com.works.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final UserService userService;

    public WebSecurityConfig(UserService userService, UserFollowInRepository userFollowInRepository) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(userService.encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and()//Rest api için bunu ekledik.
                .authorizeRequests()
                //RestApi-authority
                .antMatchers("/rest/customerinfo/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/customerlist/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/customer/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/admin/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/category/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY","BEGINNER")
                .antMatchers("/rest/changepassword/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/constant/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/customergroup/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/dashboard/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY","BEGINNER")
                .antMatchers("/rest/diary/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/forgotpassword/**").permitAll()
                .antMatchers("/rest/lab/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/payment/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/product/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/profile/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/purchase/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                // Proje ilk çalıştırıldığında kullanıcı yoksa, register kısmından kayıt yapılabilsin.
                .antMatchers("/rest/register/**").permitAll()
                .antMatchers("/rest/sale/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/statistic/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .antMatchers("/rest/supplier/**").hasAnyRole("ADMIN", "DOCTOR", "SECRETARY")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().disable()//Rest api için bunu ekledik.
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/rest/admin/logout"))
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll();
        http.headers().frameOptions().disable();
    }

    //Overloading Method
    //For Swagger
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}