package com.application.application.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {

  private Long id;

  private String message;

  private String senderUsername;

  private Long roomId;

  private Date dhMessage;

  @Builder
  public MessageDTO(Long id, String message, String senderUsername, Long roomId, Date dhMessage) {
    this.id = id;
    this.senderUsername = senderUsername;
    this.message = message;
    this.roomId = roomId;
    this.dhMessage = dhMessage;
  }


}
