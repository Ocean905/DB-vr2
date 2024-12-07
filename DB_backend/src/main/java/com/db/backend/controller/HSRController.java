package com.db.backend.controller;

import com.db.backend.model.HSR;
import com.db.backend.service.HSRService;
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
public class HSRController {
    private final HSRService hsrService;

    // GET - 獲取所有記錄
    @GetMapping("/hsr")
    public ResponseEntity<List<HSR>> getAllHSR() {
        List<HSR> results = hsrService.getAllHSR();
        return ResponseEntity.ok(results);
    }

    // POST - 創建新記錄
    @PostMapping("/hsr")
    public ResponseEntity<HSR> createHSR(@RequestBody HSR hsr) {
        HSR created = hsrService.createHSR(hsr);
        return ResponseEntity.ok(created);
    }

    // PUT - 更新記錄
    @PutMapping("/hsr/{id}")
    public ResponseEntity<HSR> updateHSR(@PathVariable String id, @RequestBody HSR hsr) {
        HSR updated = hsrService.updateHSR(id, hsr);
        return ResponseEntity.ok(updated);
    }

    // DELETE - 刪除記錄
    @DeleteMapping("/hsr/{id}")
public ResponseEntity<Void> deleteHSR(@PathVariable String id) {
    log.info("Received delete request for HSR with id: {}", id);
    try {
        hsrService.deleteHSR(id);
        log.info("Successfully deleted HSR with id: {}", id);
        return ResponseEntity.ok().build();
    } catch (Exception e) {
        log.error("Error deleting HSR with id: {}", id, e);
        return ResponseEntity.internalServerError().build();
    }
}

    // GET - 根據ID獲取記錄
    @GetMapping("/hsr/{id}")
    public ResponseEntity<HSR> getHSRById(@PathVariable String id) {
        return hsrService.getHSRById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}