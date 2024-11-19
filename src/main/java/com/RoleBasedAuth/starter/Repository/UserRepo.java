package com.RoleBasedAuth.starter.Repository;

import com.RoleBasedAuth.starter.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
