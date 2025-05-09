package ru.vsavushkin.api;

import org.openapitools.api.SendMessageToKafkaApi;
import org.openapitools.model.SendToKafkaRequest;
import org.openapitools.model.SendToKafkaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsavushkin.kafka.KafkaProducerWithMetrics;

@RestController
public class SendMessageToKafkaController implements SendMessageToKafkaApi {

    @Autowired
    KafkaProducerWithMetrics producer;

    @Override
    @GetMapping("/api/send-to-kafka")
    public ResponseEntity<SendToKafkaResponse> sendToKafka(SendToKafkaRequest sendToKafkaRequest) {

        producer.send("messages", "Send to Kafka request with id = " + sendToKafkaRequest.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
