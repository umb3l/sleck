package com.application.application.domain.repository;

import com.application.application.domain.Chatroom;
import com.application.application.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatroomRepository extends CrudRepository<Chatroom, Long> {

  @Query(value = "SELECT room " +
    "FROM Chatroom room " +
    "WHERE room.status = 1" +
    "ORDER BY room.id")
  List<Chatroom> findAllActiveRooms();

}
