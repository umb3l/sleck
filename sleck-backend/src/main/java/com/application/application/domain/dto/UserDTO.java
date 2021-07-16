package com.application.application.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserDTO {

  private Long id;

  private String username;

  private String email;

  private String password;


  @Builder
  public UserDTO(Long id, String username, String email, String password){
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
