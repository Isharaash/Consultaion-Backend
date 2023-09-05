package com.example.consultationWebBacked.repositories;

import com.example.consultationWebBacked.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users, String> {
   
    List<Users> findByEmail(String email);
}
