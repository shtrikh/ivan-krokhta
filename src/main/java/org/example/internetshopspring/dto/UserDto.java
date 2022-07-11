package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.enums.Role;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    long id;
    String login;
    String password;
    String name;
    String surname;
    Role role;
    String email;
    boolean ban;
}
