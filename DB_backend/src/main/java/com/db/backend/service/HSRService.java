package com.db.backend.service;

import com.db.backend.model.HSR;
import com.db.backend.repository.HSRRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HSRService {
    private final HSRRepository hsrRepository;

    public List<HSR> getAllHSR() {
        log.info("Fetching all HSR records");
        return hsrRepository.findAll();
    }

    public HSR createHSR(HSR hsr) {
        log.info("Creating new HSR record");
        return hsrRepository.save(hsr);
    }

    public HSR updateHSR(String id, HSR hsr) {
        log.info("Updating HSR record with id: {}", id);
        hsr.setId(id);
        return hsrRepository.save(hsr);
    }

    public void deleteHSR(String id) {
        log.info("Deleting HSR record with id: {}", id);
        hsrRepository.deleteById(id);
    }

    public Optional<HSR> getHSRById(String id) {
        log.info("Fetching HSR record with id: {}", id);
        return hsrRepository.findById(id);
    }
}