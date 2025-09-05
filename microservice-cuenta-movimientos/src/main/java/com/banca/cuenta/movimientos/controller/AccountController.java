package com.banca.cuenta.movimientos.controller;

import com.banca.cuenta.movimientos.domain.Account;
import com.banca.cuenta.movimientos.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private com.banca.cuenta.movimientos.repository.ParameterRepository parameterRepository;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account, @RequestParam Long typeParameterId) {
        com.banca.cuenta.movimientos.domain.Parameter typeParam = parameterRepository.findById(typeParameterId).orElse(null);
        if (typeParam == null) {
            return ResponseEntity.badRequest().body("Tipo de cuenta no encontrado");
        }
        account.setTypeParameter(typeParam);
        Account saved = accountRepository.save(account);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Optional<Account> acc = accountRepository.findById(id);
        return acc.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody Account updatedAccount, @RequestParam Long typeParameterId) {
        if (!accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        com.banca.cuenta.movimientos.domain.Parameter typeParam = parameterRepository.findById(typeParameterId).orElse(null);
        if (typeParam == null) {
            return ResponseEntity.badRequest().body("Tipo de cuenta no encontrado");
        }
        updatedAccount.setId(id);
        updatedAccount.setTypeParameter(typeParam);
        Account saved = accountRepository.save(updatedAccount);
        return ResponseEntity.ok(saved);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchAccount(@PathVariable Long id, @RequestBody Account partialAccount, @RequestParam(required = false) Long typeParameterId) {
        Optional<Account> accOpt = accountRepository.findById(id);
        if (accOpt.isPresent()) {
            Account acc = accOpt.get();
            if (partialAccount.getNumber() != null) acc.setNumber(partialAccount.getNumber());
            if (partialAccount.getInitialBalance() != null) acc.setInitialBalance(partialAccount.getInitialBalance());
            if (partialAccount.getStatus() != null) acc.setStatus(partialAccount.getStatus());
            if (partialAccount.getCustomer() != null) acc.setCustomer(partialAccount.getCustomer());
            if (typeParameterId != null) {
                com.banca.cuenta.movimientos.domain.Parameter typeParam = parameterRepository.findById(typeParameterId).orElse(null);
                if (typeParam == null) {
                    return ResponseEntity.badRequest().body("Tipo de cuenta no encontrado");
                }
                acc.setTypeParameter(typeParam);
            }
            Account saved = accountRepository.save(acc);
            return ResponseEntity.ok(saved);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (!accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
