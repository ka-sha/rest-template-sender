package ru.vsavushkin.api;

import org.openapitools.api.SendMessageToKafkaApi;
import org.openapitools.model.SendToKafkaRequest;
import org.openapitools.model.SendToKafkaResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageToKafkaController implements SendMessageToKafkaApi {

    @Override
    public ResponseEntity<SendToKafkaResponse> sendToKafka(SendToKafkaRequest sendToKafkaRequest) {
        return SendMessageToKafkaApi.super.sendToKafka(sendToKafkaRequest);
    }
}
