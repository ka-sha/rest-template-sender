package ru.vsavushkin.logginginterceptorstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.vsavushkin.logginginterceptorstarter.customizers.LoggingRestTemplateCustomizer;
import ru.vsavushkin.logginginterceptorstarter.service.LoggingInterceptorService;

@Configuration
public class LoggingInterceptorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LoggingInterceptorService loggerInterceptorService() {
        return new LoggingInterceptorService();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LoggingRestTemplateCustomizer loggingRestTemplateCustomizer() {
        return new LoggingRestTemplateCustomizer();
    }
}
