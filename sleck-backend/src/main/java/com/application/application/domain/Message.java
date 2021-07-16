package com.application.application.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Message {

  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String senderUsername;

  @Column
  private String message;

  @Column
  private Long roomId;

  @Column
  private Date dhMessage;

  @Builder
  public Message(Long id, String senderUsername, String message, Long roomId, Date dhMessage){
    this.id = id;
    this.senderUsername = senderUsername;
    this.message = message;
    this.roomId = roomId;
    this.dhMessage = dhMessage;
  }

}
