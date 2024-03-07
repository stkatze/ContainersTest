package com.example.demo.config;

import com.example.demo.model.DevProfile;
import com.example.demo.model.ProductionProfile;
import com.example.demo.model.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(
            value = "netology.profile.dev",
            havingValue = "true",
            matchIfMissing = true
    )
    public SystemProfile getDevProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(
            value = "netology.profile.dev",
            havingValue = "false"
    )
    public SystemProfile getProdProfile() {
        return new ProductionProfile();
    }

}