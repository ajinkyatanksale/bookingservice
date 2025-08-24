package com.atcorp.eventmanagement.configurations;

import com.atcorp.eventmanagement.util.json.RangeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public RangeModule rangeModule() {
        return new RangeModule();
    }
}

