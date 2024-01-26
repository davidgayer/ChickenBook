package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.EntityNotFoundException;
import cz.gayerdavid.ChickenBook.model.Message;
import cz.gayerdavid.ChickenBook.model.User;
import cz.gayerdavid.ChickenBook.repository.MessageRepository;
import cz.gayerdavid.ChickenBook.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Override
    public Message getMessage(@NonNull Long messageId) {
        return messageRepository.findById(messageId).get();
    }

    @Override
    public List<Message> getAllUserMessages(@NonNull Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return unwrapEntity(user, userId, User.class).getReceivedMessages();
    }

    @Override
    public Message sendMessage(Message message, @NonNull Long senderId, @NonNull Long receiverId) {
        Optional<User> sender = userRepository.findById(senderId);
        message.setSender(unwrapEntity(sender, senderId, User.class));
        Optional<User> receiver = userRepository.findById(receiverId);
        message.setReceiver(unwrapEntity(receiver, receiverId, User.class));

        return messageRepository.save(message);
    }

    private <T> T unwrapEntity(Optional<T> entity, Long id, Class<T> entityType) {
        if (entity.isPresent())
            return entity.get();
        else
            throw new EntityNotFoundException(id, entityType);
    }
}
