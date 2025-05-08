package ru.vsavushkin.logginginterceptorstarter.customizers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;
import ru.vsavushkin.logginginterceptorstarter.service.LoggingInterceptorService;

public class LoggingRestTemplateCustomizer implements RestTemplateCustomizer {

    @Autowired
    private LoggingInterceptorService loggingInterceptorService;

    @Override
    public void customize(RestTemplate restTemplate) {
        System.out.println("Подрубилось");
        restTemplate.getInterceptors().add(loggingInterceptorService);
    }
}
