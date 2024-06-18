package com.winter24.entities;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class Employee {
    private String userName;
    private String userRole;
    private String employeeName;
    private String status;
}

