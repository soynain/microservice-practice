package com.oauthserver.auth.AuthServerConfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // TODO Auto-generated method stub
        registry.addMapping("/**");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
   
}
