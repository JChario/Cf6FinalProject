package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import java.util.Optional;

public interface UserService {
    UserDTO getUserByUsername(String username);
    User saveUser(User user);
}