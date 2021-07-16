package com.application.application.service;

import com.application.application.domain.Chatroom;
import com.application.application.domain.Message;
import com.application.application.domain.User;
import com.application.application.domain.dto.ChatroomDTO;
import com.application.application.domain.dto.MessageDTO;
import com.application.application.domain.dto.UserDTO;
import com.application.application.domain.repository.ChatroomRepository;
import com.application.application.domain.repository.MessageRepository;
import com.application.application.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatroomService {

  @Autowired
  private ChatroomRepository chatroomRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private MessageRepository messageRepository;

  public long createRoom(String name, Long ownerId){

    Chatroom chat = chatroomRepository.save(Chatroom.builder().name(name).ownerId(ownerId).build());

    Optional<User> owner = userRepository.findById(ownerId);

    if(owner != null){
      owner.get().setChatroom(chat.getId());
    }

    return chat.getId();

  }

  public List<ChatroomDTO> getRooms(){

    List<Chatroom> rooms = chatroomRepository.findAllActiveRooms();

    List<ChatroomDTO> roomsDTO = new ArrayList<>();

    rooms.forEach(room -> {
      ChatroomDTO dto = ChatroomDTO.builder()
        .id(room.getId())
        .name(room.getName())
        .ownerId(room.getOwnerId())
        .build();

      roomsDTO.add(dto);
    });

    return roomsDTO;

  }

  public List<MessageDTO> getRoomMessages(Long roomId){

    List<Message> messages = messageRepository.getMessagesByRoomId(roomId);

    List<MessageDTO> messagesDTO = new ArrayList<>();

    messages.forEach( message -> {

      MessageDTO dto = MessageDTO.builder()
        .id(message.getId())
        .message(message.getMessage())
        .senderUsername(message.getSenderUsername())
        .roomId(message.getRoomId())
        .dhMessage(message.getDhMessage())
        .build();

      messagesDTO.add(dto);

    });

    return messagesDTO;

  }

  public MessageDTO sendMessage(MessageDTO message){

    Message newMessage = Message.builder().message(message.getMessage())
      .senderUsername(message.getSenderUsername()).roomId(message.getRoomId()).dhMessage(message.getDhMessage()).build();

    Message mensagemPersistida = messageRepository.save(newMessage);

    return MessageDTO.builder().id(mensagemPersistida.getId())
      .message(mensagemPersistida.getMessage()).senderUsername(mensagemPersistida.getSenderUsername())
      .roomId(mensagemPersistida.getRoomId()).dhMessage(mensagemPersistida.getDhMessage()).build();

  }

  public List<UserDTO> getCurrentUsers(Long roomId){

    List<User> users = userRepository.findAllByChatroom(roomId);

    List<UserDTO> dtos = new ArrayList<>();

    users.forEach( user -> {

      UserDTO dto = UserDTO.builder().id(user.getId())
        .username(user.getUsername()).build();

      dtos.add(dto);
    });

    return dtos;
  }

}
