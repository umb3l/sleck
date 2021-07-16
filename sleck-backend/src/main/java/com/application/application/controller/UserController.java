package com.application.application.controller;

import com.application.application.domain.User;
import com.application.application.domain.dto.LoginForm;
import com.application.application.domain.dto.UserDTO;
import com.application.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/create")
  public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){

    return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveOrUpdate(user));
  }

  @PostMapping("/authenticate")
  public ResponseEntity authenticate(@RequestBody LoginForm loginForm){

    User authenticated = userService.authenticateUser(loginForm);

    if(authenticated != null && authenticated.getPassword().equals(loginForm.getPassword())){
      return ResponseEntity.status(HttpStatus.OK).body(authenticated);
    }else{
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

  }

  @PutMapping("/setChatroom/{userId}/{roomId}")
  public ResponseEntity setChatroom(@PathVariable("userId") Long userId, @PathVariable("roomId") Long roomId){

    userService.setChatroom(userId, roomId);

    return ResponseEntity.status(HttpStatus.OK).body("");
  }

  @PutMapping("/leaveRoom/{userId}")
  public ResponseEntity leaveRoom(@PathVariable("userId") Long userId){

    userService.leaveRoom(userId);

    return ResponseEntity.status(HttpStatus.OK).body("");
  }

  @GetMapping("/checkValidUser/{username}")
  public ResponseEntity<Boolean> checkValidUser(@PathVariable("username") String username){


    return ResponseEntity.status(HttpStatus.OK).body(userService.checkValidUser(username));
  }


}
