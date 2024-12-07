package com.db.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "HSR")  // 因為已經在 MIS_Carbon 數據庫中，不需要加前綴
public class HSR {
    @Id
    private String id;
    private String origin;
    private String destination;
    private Double carbonFootprint;
}