package com.banca.cuenta.movimientos.controller;

import com.banca.cuenta.movimientos.domain.Movement;
import com.banca.cuenta.movimientos.repository.MovementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.banca.cuenta.movimientos.dto.CustomerReportDTO;
import org.springframework.format.annotation.DateTimeFormat;
import com.banca.cuenta.movimientos.service.MovementService;

@RestController
@RequestMapping("/api/v1/movements")
public class MovementController {
    @Autowired
    private com.banca.cuenta.movimientos.client.CustomerClient customerClient;

    @GetMapping("/reports/{clientId}")
    public ResponseEntity<List<com.banca.cuenta.movimientos.dto.MovementReportDTO>> getMovementsReportByUserAndDate(
            @PathVariable Long clientId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        java.time.LocalDate start = java.time.LocalDate.parse(startDate);
        java.time.LocalDate end = java.time.LocalDate.parse(endDate);
        List<Movement> movements = movementRepository.findAll();
        List<com.banca.cuenta.movimientos.dto.MovementReportDTO> report = movements.stream()
            .filter(m -> {
                com.banca.cuenta.movimientos.domain.Account acc = m.getAccount();
                return acc.getCustomer() != null && acc.getCustomer().getId() != null && acc.getCustomer().getId().equals(clientId)
                    && m.getDate() != null && !m.getDate().isBefore(start) && !m.getDate().isAfter(end);
            })
            .map(m -> {
                com.banca.cuenta.movimientos.domain.Account acc = m.getAccount();
                String customerName = "";
                try {
                    Long customerId = acc.getCustomer() != null ? acc.getCustomer().getId() : null;
                    com.banca.cuenta.movimientos.client.CustomerClient.CustomerDTO customerDTO = customerId != null ? customerClient.getCustomerById(customerId) : null;
                    if (customerDTO != null && customerDTO.name != null) {
                        customerName = customerDTO.name;
                    }
                } catch (Exception e) {
                    // Si el microservicio cliente no responde, deja el nombre vacío
                }
                com.banca.cuenta.movimientos.dto.MovementReportDTO dto = new com.banca.cuenta.movimientos.dto.MovementReportDTO();
                dto.setDate(m.getDate().toString());
                dto.setCustomerName(customerName);
                dto.setAccountNumber(acc.getNumber());
                dto.setAccountType(acc.getTypeParameter() != null ? acc.getTypeParameter().getName() : "");
                dto.setInitialBalance(acc.getInitialBalance());
                dto.setStatus(acc.getStatus());
                dto.setMovementValue(m.getValue());
                dto.setMovementType(m.getTransactionTypeParameter() != null ? m.getTransactionTypeParameter().getName() : "");
                dto.setBalance(m.getBalance());
                dto.setAvailable(m.getBalance());
                return dto;
            })
            .collect(java.util.stream.Collectors.toList());
        return ResponseEntity.ok(report);
    }


    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private com.banca.cuenta.movimientos.repository.AccountRepository accountRepository;

    @Autowired
    private com.banca.cuenta.movimientos.repository.ParameterRepository parameterRepository;

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping
    public ResponseEntity<?> createMovement(@RequestBody Movement movement, @RequestParam String accountNumber, @RequestParam Long transactionTypeParameterId) {
        com.banca.cuenta.movimientos.domain.Account account = accountRepository.findByNumber(accountNumber);
        if (account == null) {
            return ResponseEntity.badRequest().body("Cuenta no encontrada");
        }
    movement.setAccount(account);
    movement.setDate(java.time.LocalDate.now());

        com.banca.cuenta.movimientos.domain.Parameter transactionTypeParam = parameterRepository.findById(transactionTypeParameterId).orElse(null);
        if (transactionTypeParam == null) {
            return ResponseEntity.badRequest().body("Tipo de transacción no encontrado");
        }
        movement.setTransactionTypeParameter(transactionTypeParam);

        // Get last movement for this account (optimized)
        Movement lastMovement = movementRepository.findTopByAccountIdOrderByIdDesc(account.getId());
        double lastBalance;
        if (lastMovement == null) {
            lastBalance = account.getInitialBalance();
        } else {
            lastBalance = lastMovement.getBalance();
        }

        // Calculate new balance and validate withdrawal
        String typeValue = transactionTypeParam.getValue();
        Double value = movement.getValue();
        if ("DEPOSITO".equalsIgnoreCase(typeValue) || "DEPOSIT".equalsIgnoreCase(typeValue)) {
            movement.setBalance(lastBalance + value);
        } else if ("RETIRO".equalsIgnoreCase(typeValue) || "WITHDRAW".equalsIgnoreCase(typeValue)) {
            if (value > lastBalance) {
                return ResponseEntity.badRequest().body("El saldo supera al disponible");
            }
            movement.setBalance(lastBalance - value);
        } else {
            movement.setBalance(lastBalance);
        }

        // Set transactionTypeAmount field
        movement.setTransactionTypeAmount(typeValue + " " + value);

        Movement saved = movementRepository.save(movement);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Movement>> getAllMovements() {
        return ResponseEntity.ok(movementRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable Long id) {
        Optional<Movement> mov = movementRepository.findById(id);
        return mov.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        if (!movementRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        movementRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/report")
    public ResponseEntity<List<CustomerReportDTO>> getCustomerReport(
            @RequestParam Long clientId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) java.time.LocalDate endDate) {
        List<CustomerReportDTO> report = movementService.getCustomerReport(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
