package food.ordering.system.kafka.producer.service.impl;

import food.ordering.system.kafka.producer.exception.KafkaProducerException;
import food.ordering.system.kafka.producer.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;
import java.io.Serializable;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {
    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        log.info("Sending message to topic: {}, key: {}, value: {}", topicName, key, message);
        try {
            ListenableFuture<SendResult<K, V>> listenableFuture = kafkaTemplate.send(topicName, key, message);
            listenableFuture.addCallback(callback);
        } catch (KafkaException e) {
            log.error("Failed to send message to topic: {}, key: {}, value: {}, exception: {}",
                    topicName, key, message, e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    void destroy() {
        if (kafkaTemplate != null) {
            log.info("Shutting down kafka producer");
            kafkaTemplate.destroy();
        }
    }
}
