package ru.tai.service;

import org.springframework.stereotype.Service;
import ru.tai.model.Message;
import ru.tai.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("messageService")
public interface MessageService {

    List<Message> findAll();

    Message findById(Long id);

    List<Message> findAllByUserNotNullOrderByDatetimeDesc();

//    List<Message> findByUser(User user);

//    List<Message> findAllByDatetimeBetween(Date start, Date stop);

    void save(Message message);

    void deleteById(Long id);
}
