package ru.vsavushkin.logginginterceptorstarter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vsavushkin.logginginterceptorstarter.service.LoggingInterceptorService;

@Configuration
public class LoggingInterceptorAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LoggingInterceptorService loggerInterceptorService() {
        return new LoggingInterceptorService();
    }
}
