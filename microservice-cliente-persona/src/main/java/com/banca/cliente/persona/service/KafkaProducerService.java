package com.banca.cliente.persona.service;

import com.banca.cliente.persona.dto.TransaccionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final String TOPIC = "transacciones";

    private final KafkaTemplate<String, TransaccionDTO> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, TransaccionDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TransaccionDTO transaccion) {
        kafkaTemplate.send(TOPIC, transaccion);
    }
}
