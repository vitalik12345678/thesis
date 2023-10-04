package com.example.thesis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories
public class AppConfig {

    @Bean
    public CustomAuditorAware auditorAware() {
        return new CustomAuditorAware();
    }
}
