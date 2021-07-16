package com.application.application.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatroomDTO {

  private Long id;

  private String name;

  private Long ownerId;

  private int status;

  @Builder
  public ChatroomDTO(Long id, String name, int status, long ownerId) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.ownerId = ownerId;
  }


}
