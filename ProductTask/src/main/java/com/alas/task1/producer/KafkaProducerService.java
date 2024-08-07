package com.alas.task1.producer;

import com.alas.task1.dto.country.request.CountryResponseDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    KafkaTemplate<String, com.amiltech.commen.dto.CountryResponseDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, com.amiltech.commen.dto.CountryResponseDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, com.amiltech.commen.dto.CountryResponseDTO dto) {
        kafkaTemplate.send(topic, dto);
    }
}
