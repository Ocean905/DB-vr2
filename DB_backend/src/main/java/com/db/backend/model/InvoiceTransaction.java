package com.db.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Data
@Document(collection = "invoice_transactions")
public class InvoiceTransaction {
    @Id
    private String id = new ObjectId().toString(); // 直接在宣告時初始化
    private String name;
    private String unit;
    private Double coefficient;
    private String category;
    private String source;
    private String source_name;  

    // 如果還需要自定義建構子
    public InvoiceTransaction() {
        if (this.id == null) {
            this.id = new ObjectId().toString();
        }
    }
}