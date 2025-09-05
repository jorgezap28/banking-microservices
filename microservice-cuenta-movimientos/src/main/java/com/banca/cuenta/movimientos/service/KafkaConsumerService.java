package com.banca.cuenta.movimientos.service;

import com.banca.cuenta.movimientos.dto.TransaccionDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "transacciones", groupId = "cuenta-movimientos-group")
    public void listen(TransaccionDTO transaccion) {
        // Procesa el objeto recibido
        logger.info("Transacción recibida desde Kafka: {} - {} - {}", transaccion.getId(), transaccion.getTipo(), transaccion.getMonto());
    }
}
