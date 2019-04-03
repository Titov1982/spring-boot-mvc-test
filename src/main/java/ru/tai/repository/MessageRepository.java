package ru.tai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tai.model.Message;
import ru.tai.model.User;

import java.util.Date;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByUser(User user);

    List<Message> findAllByDatetimeBetween(Date start, Date stop);

//    @Query("from message mes order by mes.datetime desc")
//    List<Message> findAllOrderByDatetime();
    List<Message> findAllByUserNotNullOrderByDatetimeDesc();

}
