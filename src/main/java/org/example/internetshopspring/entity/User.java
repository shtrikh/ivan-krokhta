package org.example.internetshopspring.entity;

import lombok.*;
import org.example.internetshopspring.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "ban")
    private boolean ban;
}
