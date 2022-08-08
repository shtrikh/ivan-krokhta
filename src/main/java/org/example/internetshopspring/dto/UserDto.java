package org.example.internetshopspring.dto;

import lombok.*;
import org.example.internetshopspring.dto.group.OnCreate;
import org.example.internetshopspring.dto.group.OnUpdate;
import org.example.internetshopspring.enums.Role;
import org.example.internetshopspring.validator.FirstNameConstraint;
import org.example.internetshopspring.validator.LastNameConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @NotBlank(groups = OnCreate.class)
    private String login;

    @NotBlank(groups = OnCreate.class)
    private String password;

    @FirstNameConstraint(groups = OnCreate.class)
    @NotBlank(groups = OnCreate.class)
    private String name;

    @LastNameConstraint(groups = OnCreate.class)
    @NotBlank(groups = OnCreate.class)
    private String surname;

    private Role role;

    @Email(groups = OnCreate.class)
    @NotBlank(groups = OnCreate.class)
    private String email;

    private boolean ban;
}
