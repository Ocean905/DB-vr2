package com.db.backend.controller;

import com.db.backend.model.InvoiceTransaction;
import com.db.backend.service.InvoiceTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class InvoiceTransactionController {
    private final InvoiceTransactionService transactionService;

    @GetMapping("/transactions")
    public ResponseEntity<List<InvoiceTransaction>> getAllTransactions() {
        List<InvoiceTransaction> results = transactionService.getAllTransactions();
        log.info("Fetched {} transactions", results.size());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<InvoiceTransaction> getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/transactions")
    public ResponseEntity<InvoiceTransaction> createTransaction(@RequestBody InvoiceTransaction transaction) {
        InvoiceTransaction created = transactionService.createTransaction(transaction);
        log.info("Created transaction with id: {}", created.getId());
        return ResponseEntity.ok(created);
    }

    @PutMapping("/transactions/{id}")
    public ResponseEntity<InvoiceTransaction> updateTransaction(
            @PathVariable String id,
            @RequestBody InvoiceTransaction transaction) {
        InvoiceTransaction updated = transactionService.updateTransaction(id, transaction);
        log.info("Updated transaction with id: {}", id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/transactions/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable String id) {
        log.info("接收刪除請求，ID: {}", id);
        
        if (id == null || id.trim().isEmpty()) {
            log.error("刪除失敗：ID為空");
            return ResponseEntity.badRequest().build();
        }

        try {
            boolean exists = transactionService.getTransactionById(id).isPresent();
            if (!exists) {
                log.error("刪除失敗：找不到ID為 {} 的記錄", id);
                return ResponseEntity.notFound().build();
            }

            transactionService.deleteTransaction(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("刪除過程發生異常，ID: {}, 錯誤: {}", id, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}