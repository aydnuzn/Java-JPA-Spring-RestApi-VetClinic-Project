package com.works.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {
    @Bean
    public AuditorAware<String> auditorProvider() {
        AuditorAware<String> auditor = new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                String uname = SecurityContextHolder.getContext().getAuthentication().getName();
                return Optional.ofNullable(uname);
            }
        };
        return auditor;
    }
}
