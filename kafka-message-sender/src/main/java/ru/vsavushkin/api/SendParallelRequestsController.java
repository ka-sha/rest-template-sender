package ru.vsavushkin.api;

import org.openapitools.api.SendParallelRequestsApi;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SendParallelRequestsController implements SendParallelRequestsApi {

    private final static RestTemplate restTemplate = new RestTemplate();
    private final static ExecutorService executor = Executors.newFixedThreadPool(4);

    @Override
    public ResponseEntity<Void> sendParallelRequests(String body) {

        final HttpEntity<Void> request = null;
        final String url = "https://fakestoreapi.com/products";
        CompletableFuture<ResponseEntity<Void>> future = CompletableFuture.supplyAsync(
                () -> restTemplate.exchange(url, HttpMethod.GET, request, Void.class), executor);
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
