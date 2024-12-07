package com.db.backend.repository;

import com.db.backend.model.InvoiceTransactionCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceTransactionCategoryRepository extends MongoRepository<InvoiceTransactionCategory, String> {
}
