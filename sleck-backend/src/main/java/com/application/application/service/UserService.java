package com.application.application.service;

import com.application.application.domain.User;
import com.application.application.domain.dto.LoginForm;
import com.application.application.domain.dto.UserDTO;
import com.application.application.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public UserDTO saveOrUpdate(UserDTO user)
  {
    User newUser = User.builder().username(user.getUsername()).email(user.getEmail())
      .password(user.getPassword()).build();

    User userPersistido = userRepository.save(newUser);

    return UserDTO.builder()
      .username(userPersistido.getUsername())
      .id(userPersistido.getId())
      .email(userPersistido.getEmail())
      .password(userPersistido.getPassword()).build();
  }

  public User authenticateUser(LoginForm loginForm){

    User user = userRepository.findByEmail(loginForm.getEmail());

    return user;

  }

  public void setChatroom(Long userId, Long roomId){

    Optional<User> user = userRepository.findById(userId);

    if(user != null){
      user.get().setChatroom(roomId);
      userRepository.save(user.get());
    }

  }

  public void leaveRoom(Long userId){

    Optional<User> user = userRepository.findById(userId);

    if(user != null){
      user.get().setChatroom(Long.valueOf(0));
      userRepository.save(user.get());
    }

  }

  public boolean checkValidUser(String username){

    List<User> user=  userRepository.findAllByUsername(username);

    if(!user.isEmpty()){
      return false;
    }else{
      return true;
    }

  }

}
