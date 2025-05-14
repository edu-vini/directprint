package com.viasolutions.directprint;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // permite qualquer origem
                .allowedMethods("*") // permite qualquer método (GET, POST, etc.)
                .allowedHeaders("*"); // permite qualquer cabeçalho
    }
}
