package com.db.backend.repository;

import com.db.backend.model.InvoiceTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTransactionRepository extends MongoRepository<InvoiceTransaction, String> {
}
