package ru.vsavushkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

@Service
public class SendParallelRequestsService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url1}")
    private String url1;

    @Value("${url2}")
    private String url2;

    private final static ExecutorService executor = Executors.newFixedThreadPool(4);

    public CompletableFuture<List<ResponseEntity<Void>>> send() {

        CompletableFuture<ResponseEntity<Void>> request1 = CompletableFuture.supplyAsync(
                () -> restTemplate.exchange(url1, HttpMethod.GET, null, Void.class), executor);
        CompletableFuture<ResponseEntity<Void>> request2 = CompletableFuture.supplyAsync(
                () -> restTemplate.exchange(url2, HttpMethod.GET, null, Void.class), executor);

        return CompletableFuture.allOf(request1, request2)
                .thenApply(v -> Stream.of(request1, request2)
                        .map(CompletableFuture::join)
                        .filter(Objects::nonNull)
                        .toList());
    }
}
