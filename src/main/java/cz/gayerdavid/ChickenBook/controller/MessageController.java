package cz.gayerdavid.ChickenBook.controller;

import org.springframework.web.bind.annotation.RestController;

import cz.gayerdavid.ChickenBook.model.Message;
import cz.gayerdavid.ChickenBook.service.MessageService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Long messageId) {
        return new ResponseEntity<>(messageService.getMessage(messageId), HttpStatus.OK);
    }

    @GetMapping("/inbox/user/{userId}")
    public ResponseEntity<List<Message>> getInboxMessages(@PathVariable Long userId) {
        return new ResponseEntity<>(messageService.getUserInboxMessages(userId), HttpStatus.OK);
    }

    @GetMapping("/outbox/user/{userId}")
    public ResponseEntity<List<Message>> getOutboxMessages(@PathVariable Long userId) {
        return new ResponseEntity<>(messageService.getUserOutboxMessages(userId), HttpStatus.OK);
    }

    @PostMapping("/sender/{senderId}/receiver/{receiverId}")
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, @PathVariable Long senderId,
            @PathVariable Long receiverId) {
                return new ResponseEntity<>(messageService.sendMessage(message, senderId, receiverId), HttpStatus.CREATED);
    }

}
