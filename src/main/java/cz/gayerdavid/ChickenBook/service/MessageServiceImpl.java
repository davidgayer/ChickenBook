package cz.gayerdavid.ChickenBook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.gayerdavid.ChickenBook.exception.UserNotFoundException;
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
        if (user.isPresent()) {
            return user.get().getReceivedMessages();
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public Message sendMessage(Message message, @NonNull Long senderId, @NonNull Long receiverId) {
        Optional<User> sender = userRepository.findById(senderId);
        if (sender.isPresent()) {
            message.setSender(sender.get());
        } else {
            throw new UserNotFoundException(senderId);
        }
        Optional<User> receiver = userRepository.findById(receiverId);
        if (receiver.isPresent()) {
            message.setReceiver(receiver.get());
        } else {
            throw new UserNotFoundException(receiverId);
        }

        return messageRepository.save(message);
    }
}
