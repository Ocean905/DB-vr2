// InvoiceTransactionCategory.java
package com.db.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "invoice_transtions_categories")
public class InvoiceTransactionCategory {
    @Id
    private String id;
    private String name;
    private String parent;
}