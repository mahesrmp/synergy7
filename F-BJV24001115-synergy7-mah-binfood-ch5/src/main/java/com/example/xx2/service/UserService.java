package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Users;
import com.example.xx2.model.dto.UserCreateDto;
import com.example.xx2.model.dto.UserDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<Users> getAll();

    UserDto create(UserCreateDto userCreateDto);

    Users update(UUID idUsers, Users users);

    void deleteUser(UUID id);

    Users getUserById(UUID userId);
}
