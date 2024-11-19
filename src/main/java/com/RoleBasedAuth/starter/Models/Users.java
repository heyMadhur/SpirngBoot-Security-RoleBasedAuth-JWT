package com.RoleBasedAuth.starter.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username, password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;  // e.g., "ROLE_ADMIN", "ROLE_USER"

}
