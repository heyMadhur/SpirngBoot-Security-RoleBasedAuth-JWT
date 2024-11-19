package com.RoleBasedAuth.starter.Service;

import com.RoleBasedAuth.starter.JWT.JWTService;
import com.RoleBasedAuth.starter.Models.LoginUserDTO;
import com.RoleBasedAuth.starter.Models.RegistrationUserDTO;
import com.RoleBasedAuth.starter.Models.Users;
import com.RoleBasedAuth.starter.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;


    public Users registerUser(RegistrationUserDTO registrationUser) {
        Users user = new Users();
        Set<String> roles= new HashSet<>();
        for(String role: registrationUser.getRoles()){
            roles.add("ROLE_"+role);
            System.out.println("ROLE_"+role);
        }
        user.setRoles(roles);
        user.setUsername(registrationUser.getUsername());
        user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
        return userRepo.save(user);
    }

    public String verifyUser(LoginUserDTO loginUser) {
        Optional<Users> existingUser= userRepo.findByUsername(loginUser.getUsername());
        System.out.println("Verify User in Auth Service Called");

        if(existingUser.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }

        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));

        if(authentication.isAuthenticated()) {
            System.out.println("User is Authenticated and Token genereration process is started");
            return jwtService.generateToken(existingUser.get());
//            return "User Authenticated";
        }

        return "Details are wrong";
    }
}
