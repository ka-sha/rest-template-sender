package ru.vsavushkin.api;

import org.openapitools.api.SendMessageToKafkaApi;
import org.openapitools.model.SendToKafkaRequest;
import org.openapitools.model.SendToKafkaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.vsavushkin.kafka.KafkaProducerWithMetrics;
import ru.vsavushkin.logginginterceptorstarter.service.LoggingInterceptorService;

@RestController
public class SendMessageToKafkaController implements SendMessageToKafkaApi {

    @Autowired
    LoggingInterceptorService loggingService;

    @Autowired
    KafkaProducerWithMetrics producer;

    @Override
    public ResponseEntity<SendToKafkaResponse> sendToKafka(SendToKafkaRequest sendToKafkaRequest) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(loggingService);

        producer.send("messages", "Send to Kafka request with id = " + sendToKafkaRequest.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
