package com.example.skilllinkbackend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "skilllink.cors")
public class WebConfig {

    private String[] allowedOrigins;
    private String[] allowedMethods;
    private String[] allowedHeaders;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins != null ? allowedOrigins : new String[0])
                        .allowedMethods(allowedMethods != null ? allowedMethods : new String[]{"GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"})
                        .allowedHeaders(allowedHeaders != null ? allowedHeaders : new String[]{"Origin", "Content-Type", "Accept", "Authorization", "X-Requested-With"})
                        .exposedHeaders("Authorization")
                        .allowCredentials(true)
                        .maxAge(3600L);
            }
        };
    }

    // Getters and setters...
    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
    public void setAllowedMethods(String[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }
    public void setAllowedHeaders(String[] allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }
    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }
    public String[] getAllowedMethods() {
        return allowedMethods;
    }
    public String[] getAllowedHeaders() {
        return allowedHeaders;
    }
}