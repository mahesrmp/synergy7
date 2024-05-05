package org.example.service;

import org.example.model.entity.Role;

public interface UserService {
    String register(String username, String email_address, String password, Role role);

    String login(String email_address, String password);

}
