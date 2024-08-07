package com.amiltech.task2.listener;

import com.amiltech.commen.dto.CountryResponseDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test2t", groupId = "test2")
    public void listen(CountryResponseDTO dto) {
        System.out.println("Received message: " + dto);
    }
}
