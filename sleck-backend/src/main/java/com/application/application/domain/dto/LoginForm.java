package com.application.application.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginForm {

  private String email;

  private String password;

  @Builder
  public LoginForm(String email, String password){
    this.email = email;
    this.password = password;
  }
}
