package io.ryos.app.msg.controller;

import io.ryos.app.msg.data.Message;
import io.ryos.app.msg.repo.MessageRepository;
import io.ryos.app.policy.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private RateLimiter rateLimiter;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getMessagesForUser(@PathParam("userId") String userId) {
        final List<Message> messages = messageRepository.getMessageListFor(userId);
        return ResponseEntity.ok(messages);
    }
}
