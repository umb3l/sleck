package com.application.application.controller;

import com.application.application.domain.Chatroom;
import com.application.application.domain.Message;
import com.application.application.domain.dto.ChatroomDTO;
import com.application.application.domain.dto.MessageDTO;
import com.application.application.domain.dto.UserDTO;
import com.application.application.service.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/chatroom")
public class ChatroomController {

  @Autowired
  private ChatroomService chatroomService;

  @PostMapping("/create/{name}/{id}")
  public ResponseEntity createUser(@PathVariable("name") String name, @PathVariable("id") Long id){

    return ResponseEntity.status(HttpStatus.CREATED).body(chatroomService.createRoom(name, id));
  }

  @GetMapping("/getRooms")
  public ResponseEntity<List<ChatroomDTO>> getRooms(){

    return ResponseEntity.status(HttpStatus.OK).body(chatroomService.getRooms());
  }

  @GetMapping("/getMessages/{roomId}")
  public ResponseEntity<List<MessageDTO>> getRoomMessages(@PathVariable("roomId") Long roomId){

    return ResponseEntity.status(HttpStatus.OK).body(chatroomService.getRoomMessages(roomId));
  }

  @PostMapping("/sendMessage")
  public ResponseEntity<MessageDTO> sendMessage(@RequestBody MessageDTO message){

    return ResponseEntity.status(HttpStatus.OK).body(chatroomService.sendMessage(message));

  }

  @GetMapping("/getCurrentUsers/{roomId}")
  public ResponseEntity<List<UserDTO>> getCurrentUsers(@PathVariable("roomId") Long roomId){


    return ResponseEntity.status(HttpStatus.OK).body(chatroomService.getCurrentUsers(roomId));
  }

}
