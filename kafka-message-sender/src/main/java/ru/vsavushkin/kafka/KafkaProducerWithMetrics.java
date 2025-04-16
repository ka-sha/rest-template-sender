package ru.vsavushkin.kafka;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class KafkaProducerWithMetrics {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final Counter successCounter;
    private final Counter errorCounter;

    @Autowired
    public KafkaProducerWithMetrics(KafkaTemplate<String, String> kafkaTemplate, MeterRegistry meterRegistry) {

        this.kafkaTemplate = kafkaTemplate;
        this.successCounter = Counter.builder("kafka_messages_sent_success")
                .description("Количество успешно отправленных сообщений в Kafka")
                .register(meterRegistry);
        this.errorCounter = Counter.builder("kafka_messages_sent_error")
                .description("Количество ошибок при отправке сообщений в Kafka")
                .register(meterRegistry);
    }

    public void send(String topic, String message) {
        kafkaTemplate.send(topic, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        successCounter.increment();
                    } else {
                        errorCounter.increment();
                        System.err.println("Ошибка при отправке в Kafka: " + ex.getMessage());
                    }
        });
    }
}
