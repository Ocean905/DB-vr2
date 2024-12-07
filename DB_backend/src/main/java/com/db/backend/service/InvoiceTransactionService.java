package com.db.backend.service;

import com.db.backend.model.InvoiceTransaction;
import com.db.backend.repository.InvoiceTransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceTransactionService {
    @Autowired
private final InvoiceTransactionRepository repository; 
    
    public List<InvoiceTransaction> getAllTransactions() {
        log.info("Fetching all transactions");
        return repository.findAll();
    }

    public Optional<InvoiceTransaction> getTransactionById(String id) {
        log.info("Fetching transaction with id: {}", id);
        return repository.findById(id);
    }

    public InvoiceTransaction createTransaction(InvoiceTransaction transaction) {
        log.info("Creating new transaction");
        // 確保新文檔不會有 undefined ID
        if (transaction.getId() == null || transaction.getId().equals("undefined")) {
            transaction.setId(new ObjectId().toString());
        }
        return repository.save(transaction);
    }

    public InvoiceTransaction updateTransaction(String id, InvoiceTransaction transaction) {
        log.info("Updating transaction with id: {}", id);
        // 確保更新時使用正確的ID
        transaction.setId(id);
        return repository.save(transaction);
    }

    // 修改 InvoiceTransactionService.java
    public void deleteTransaction(String id) {
        log.info("Deleting transaction with id: {}", id);
        try {
            Optional<InvoiceTransaction> transaction = repository.findById(id);
            if (transaction.isEmpty()) {
                throw new RuntimeException("Transaction not found with id: " + id);
            }
            
            repository.deleteById(id);
            
            // 驗證刪除
            if (repository.existsById(id)) {
                throw new RuntimeException("Failed to delete transaction");
            }
            
            log.info("Successfully deleted transaction");
        } catch (Exception e) {
            log.error("Error deleting transaction: {}", e.getMessage());
            throw new RuntimeException("Failed to delete transaction: " + e.getMessage());
        }
    }
}