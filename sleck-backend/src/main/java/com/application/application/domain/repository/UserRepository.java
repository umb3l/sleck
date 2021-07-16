package com.application.application.domain.repository;

import com.application.application.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByEmail(String email);

  List<User> findAllByUsername(String username);

  @Query(value = "SELECT user " +
    "FROM User user " +
    "WHERE user.chatroom = ?1 ")
  List<User> findAllByChatroom(Long roomId);

}
