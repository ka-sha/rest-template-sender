package ru.vsavushkin.api;

import org.openapitools.api.SendParallelRequestsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsavushkin.service.SendParallelRequestsService;

@RestController
public class SendParallelRequestsController implements SendParallelRequestsApi {

    @Autowired
    private SendParallelRequestsService parallelRequestsService;

    @Override
    @GetMapping("/api/send-parallel")
    public ResponseEntity<Void> sendParallelRequests(String body) {

        parallelRequestsService.send();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
