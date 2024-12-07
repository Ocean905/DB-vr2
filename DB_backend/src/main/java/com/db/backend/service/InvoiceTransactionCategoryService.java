package com.db.backend.service;

import com.db.backend.model.InvoiceTransactionCategory;
import com.db.backend.repository.InvoiceTransactionCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceTransactionCategoryService {
    private final InvoiceTransactionCategoryRepository repository;

    public List<InvoiceTransactionCategory> getAllCategories() {
        log.info("Fetching all categories");
        return repository.findAll();
    }

    public Optional<InvoiceTransactionCategory> getCategoryById(String id) {
        log.info("Fetching category with id: {}", id);
        return repository.findById(id);
    }

    public InvoiceTransactionCategory createCategory(InvoiceTransactionCategory category) {
        log.info("Creating new category");
        return repository.save(category);
    }

    public InvoiceTransactionCategory updateCategory(String id, InvoiceTransactionCategory category) {
        log.info("Updating category with id: {}", id);
        category.setId(id);
        return repository.save(category);
    }

    public void deleteCategory(String id) {
        log.info("Deleting category with id: {}", id);
        repository.deleteById(id);
    }
}