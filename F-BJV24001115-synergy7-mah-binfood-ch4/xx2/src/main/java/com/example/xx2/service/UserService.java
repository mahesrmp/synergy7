package com.example.xx2.service;

import com.example.xx2.model.Role;
import com.example.xx2.model.Users;

public interface UserService {
    String register(String username, String email, String password, Role role);

    String login(String email, String password);

}
