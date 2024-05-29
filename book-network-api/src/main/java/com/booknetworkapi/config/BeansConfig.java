package com.booknetworkapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class BeansConfig {

    @Bean(name = "AuditAwareBean")
    public AuditorAware<Long> auditorAware(){
        return new ApplicationAuditAware();
    }
}
