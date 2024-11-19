package com.RoleBasedAuth.starter.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserDTO {


    private String username, password;

    private Set<String> roles;  // e.g., {"USER", "ADMIN"}

}
