package ru.vsavushkin.logginginterceptorstarter.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

public class LoggingInterceptorService implements ClientHttpRequestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptorService.class);

    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull HttpRequest request,
                                        @NonNull byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);
        return response;
    }

    private void logRequest(HttpRequest request, byte[] body) {
        logger.info("Request method: {}, URI: {}", request.getMethod(), request.getURI());
        logger.info("Request headers: {}", request.getHeaders());
        logger.info("Request body: {}", body);
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        logger.info("Response code: {}, Response text: {}", response.getStatusCode(), response.getStatusText());
        logger.info("Response headers: {}", response.getHeaders());
        logger.info("Response body: {}", response.getBody());
    }
}
