package com.atcorp.eventmanagement.configurations;

import com.atcorp.eventmanagement.util.interceptor.ServiceAuthInterceptor;
import com.atcorp.eventmanagement.util.jwt.JwtHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Primary
    public RestTemplate getRestTemplateWithInterceptor(JwtHelper jwtHelper) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(new ServiceAuthInterceptor(jwtHelper)));
        return restTemplate;
    }

}
