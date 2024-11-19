package com.RoleBasedAuth.starter.Service;

import com.RoleBasedAuth.starter.Models.UserPrincipal;
import com.RoleBasedAuth.starter.Models.Users;
import com.RoleBasedAuth.starter.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user=  userRepo.findByUsername(username);
        if (user.isPresent()) {
            return new UserPrincipal(user.get());
        }

        throw new UsernameNotFoundException("User Not Found");
    }
}
