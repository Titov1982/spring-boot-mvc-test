package ru.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tai.model.Message;
import ru.tai.model.User;
import ru.tai.repository.MessageRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    @Transactional
    public List<Message> findAll() {
        List<Message> messages = messageRepository.findAll();
        return messages;
    }

    @Override
    @Transactional
    public Message findById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        return message.get();
    }

    /**
     * Метод выбирает все сообщения у которых пользователь не NULL и сортирует их по времени и дате в обратном порядке,
     * т.е. с начала будут самые новые сообщения.
     * @return
     */
    @Override
    @Transactional
    public List<Message> findAllByUserNotNullOrderByDatetimeDesc() {
        List<Message> messages = messageRepository.findAllByUserNotNullOrderByDatetimeDesc();
        return messages;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<Message> findByUser(User user) {
//        List<Message> messages = messageRepository.findByUser(user);
//        return messages;
//    }
//
//    @Override
//    @Transactional
//    public List<Message> findAllByDatetimeBetween(Date start, Date stop) {
//        return messageRepository.findAllByDatetimeBetween(start, stop);
//    }

    @Override
    @Transactional
    public void save(Message message) {
        messageRepository.save(message);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        message.get().setUser(null);
        messageRepository.delete(message.get());
    }
}
