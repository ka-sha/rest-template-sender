package ru.vsavushkin.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendParallelRequestsService {

    @Autowired
    private RestTemplate loggingRestTemplate;

    @Value("${url1}")
    private String url1;

    @Value("${url2}")
    private String url2;

    private final static ExecutorService executor = Executors.newFixedThreadPool(4);

    public void send() {

        CompletableFuture<String> request1 =
                CompletableFuture.supplyAsync(() -> loggingRestTemplate.getForObject(url1, String.class), executor);
        CompletableFuture<String> request2 =
                CompletableFuture.supplyAsync(() -> loggingRestTemplate.getForObject(url2, String.class), executor);

        CompletableFuture.allOf(request1, request2)
                .thenAccept(_ -> {
                    try {
                        request1.get();
                        request2.get();
                    } catch (Exception e) {
                        System.out.println("Have some problems");
                    }
                });
    }
}
