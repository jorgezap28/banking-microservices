package com.banca.cuenta.movimientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCuentaMovimientosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCuentaMovimientosApplication.class, args);
    }
}
