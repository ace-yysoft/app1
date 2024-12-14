package com.yysoft.mydigitallink.message;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MongoTemplate mongoTemplate;

    // 메시지 생성
    @PostMapping
    public ResponseEntity<Message> create(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now().toString());
        Message savedMessage = mongoTemplate.save(message);
        return ResponseEntity.ok(savedMessage);
    }

    // 전체 메시지 조회
    @GetMapping
    public ResponseEntity<List<Message>> findAll() {
        List<Message> messages = mongoTemplate.findAll(Message.class);
        return ResponseEntity.ok(messages);
    }

    // 단일 메시지 조회
    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable String id) {
        Message message = mongoTemplate.findById(id, Message.class);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(message);
    }

    // 메시지 수정
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable String id, @RequestBody Message message) {
        Message existingMessage = mongoTemplate.findById(id, Message.class);
        if (existingMessage == null) {
            return ResponseEntity.notFound().build();
        }
        message.setId(id);
        message.setCreatedAt(existingMessage.getCreatedAt());
        Message updatedMessage = mongoTemplate.save(message);
        return ResponseEntity.ok(updatedMessage);
    }

    // 메시지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Message message = mongoTemplate.findById(id, Message.class);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, Message.class);
        return ResponseEntity.ok().build();
    }
}
