package com.application.application.domain;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
public class Chatroom {

  @Id
  @Column(unique = true, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private int status;

  @Column
  private long ownerId;

  @Builder
  public Chatroom(Long id, String name, int status, long ownerId) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.ownerId = ownerId;
  }

  @PrePersist
  protected void onCreate() {
    if (this.status == 0) {
      this.status = 1;
    }
  }

}
