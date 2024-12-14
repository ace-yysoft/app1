package com.yysoft.mydigitallink.gs1;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/gs1")
public class Gs1Controller {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    public ResponseEntity<Gs1DigitalLink> create(@RequestBody Gs1DigitalLink link) {
        link.setCreatedAt(LocalDateTime.now().toString());
        Gs1DigitalLink savedLink = mongoTemplate.save(link);
        return ResponseEntity.ok(savedLink);
    }

    @GetMapping
    public ResponseEntity<List<Gs1DigitalLink>> findAll() {
        List<Gs1DigitalLink> links = mongoTemplate.findAll(Gs1DigitalLink.class);
        return ResponseEntity.ok(links);
    }

    @GetMapping("/{gtin}")
    public ResponseEntity<Gs1DigitalLink> findByGtin(@PathVariable String gtin) {
        Gs1DigitalLink link = mongoTemplate.findById(gtin, Gs1DigitalLink.class);
        return ResponseEntity.ok(link);
    }
} 