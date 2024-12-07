package com.db.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MIS_Carbon.invoice_transtions_categories")  // 修改這裡，加入完整路徑
public class InvoiceTransactionCategory {
    @Id
    private String id;
    private String name;
    private String parent;
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getParent() { return parent; }
    public void setParent(String parent) { this.parent = parent; }
}