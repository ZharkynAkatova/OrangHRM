package com.winter24.entities;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AdminEntity {
  private String inputUserName;
  private String inputEmployeeName;
  private String userRole;
  private String status;

}
