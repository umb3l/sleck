package com.application.application.domain.repository;

import com.application.application.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

  @Query(value = "SELECT message " +
    "FROM Message message " +
    "WHERE message.roomId = ?1 " +
    "ORDER BY message.dhMessage")
  List<Message> getMessagesByRoomId(Long id);

}
