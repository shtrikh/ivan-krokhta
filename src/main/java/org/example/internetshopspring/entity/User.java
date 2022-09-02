package org.example.internetshopspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.example.internetshopspring.enums.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@EqualsAndHashCode
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "ban")
    private boolean ban;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<Order> orders;
}
