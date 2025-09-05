package com.banca.cuenta.movimientos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "http://localhost:8082")
public interface CustomerClient {
    @GetMapping("/api/v1/customers/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") Long id);

    class CustomerDTO {
        public Long id;
        public String name;
    }
}
