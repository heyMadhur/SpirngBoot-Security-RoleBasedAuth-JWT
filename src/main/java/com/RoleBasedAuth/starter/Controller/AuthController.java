package com.RoleBasedAuth.starter.Controller;


import com.RoleBasedAuth.starter.Models.LoginUserDTO;
import com.RoleBasedAuth.starter.Models.RegistrationUserDTO;
import com.RoleBasedAuth.starter.Models.Users;
import com.RoleBasedAuth.starter.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody RegistrationUserDTO registrationUser){
        return authService.registerUser(registrationUser);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginUserDTO loginUser) {
        return authService.verifyUser(loginUser);
    }

}
