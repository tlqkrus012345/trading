package com.trading.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessagePublisher {
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public <T> void publish(KafkaTopic topic, Long key, T body) {
        try {
            String content = objectMapper.writeValueAsString(body);
            kafkaTemplate.send(topic.getTopic(), key, content);
            log.info("kafka message published. topic: {}, key: {}, body: {}", topic, key, content);
        } catch (JsonProcessingException e) {
            log.error("[!] KafkaMessagePublisher.publish() failed. ", e);
        }
    }
}
