package com.db.backend.repository;

import com.db.backend.model.HSR;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface HSRRepository extends MongoRepository<HSR, String> {
    @Query(value = "{ }", fields = "{ 'origin' : 1, 'destination' : 1, 'carbonFootprint' : 1}")
    List<HSR> findAllProjected();
}