package com.example.xx2.service;

import com.example.xx2.model.Merchant;
import com.example.xx2.model.Users;
import com.example.xx2.model.dto.MerchantDto;
import com.example.xx2.model.dto.UserCreateDto;
import com.example.xx2.model.dto.UserDto;
import com.example.xx2.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Users> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        Users users = new Users();
        users.setUsername(userCreateDto.getUsername());
        users.setEmail(userCreateDto.getEmail());
        users.setPassword((userCreateDto.getPassword()));
        userRepository.save(users);

        return modelMapper.map(users, UserDto.class);
    }

    @Override
    public Users update(UUID idUsers, Users users) {
        users.setId(idUsers);
        return userRepository.save(users);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users getUserById(UUID userId) {
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }
}
