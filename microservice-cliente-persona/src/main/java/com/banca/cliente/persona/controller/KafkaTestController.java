package com.banca.cliente.persona.controller;

import com.banca.cliente.persona.dto.TransaccionDTO;
import com.banca.cliente.persona.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaTestController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/send")
    public String sendMessage(@RequestBody TransaccionDTO transaccion) {
        kafkaProducerService.sendMessage(transaccion);
        return "Transacción enviada a Kafka";
    }
}
