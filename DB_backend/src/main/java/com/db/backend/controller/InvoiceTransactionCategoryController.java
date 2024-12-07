package com.db.backend.controller;

import com.db.backend.model.InvoiceTransactionCategory;
import com.db.backend.service.InvoiceTransactionCategoryService;
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
public class InvoiceTransactionCategoryController {
    private final InvoiceTransactionCategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<InvoiceTransactionCategory>> getAllCategories() {
        List<InvoiceTransactionCategory> results = categoryService.getAllCategories();
        log.info("Fetched {} categories", results.size());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<InvoiceTransactionCategory> getCategoryById(@PathVariable String id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/categories")
    public ResponseEntity<InvoiceTransactionCategory> createCategory(@RequestBody InvoiceTransactionCategory category) {
        InvoiceTransactionCategory created = categoryService.createCategory(category);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<InvoiceTransactionCategory> updateCategory(
            @PathVariable String id,
            @RequestBody InvoiceTransactionCategory category) {
        InvoiceTransactionCategory updated = categoryService.updateCategory(id, category);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}