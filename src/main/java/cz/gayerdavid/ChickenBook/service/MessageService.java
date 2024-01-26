package cz.gayerdavid.ChickenBook.service;

import java.util.List;

import cz.gayerdavid.ChickenBook.model.Message;

public interface MessageService {

    Message getMessage(Long messageId);

    List<Message> getUserInboxMessages(Long userId);

    List<Message> getUserOutboxMessages(Long userId);

    Message sendMessage(Message message, Long senderId, Long receiverId);

}
    

