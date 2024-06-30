package com.example.xx2.controller;

import com.example.xx2.model.Product;
import com.example.xx2.model.Users;
import com.example.xx2.model.dto.MerchantCreateDto;
import com.example.xx2.model.dto.MerchantDto;
import com.example.xx2.model.dto.UserCreateDto;
import com.example.xx2.model.dto.UserDto;
import com.example.xx2.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserDto> getAllUserDto(){
        return userService.getAll()
                .stream()
                .map(users -> modelMapper.map(users, UserDto.class))
                .toList();
    }

//    @PostMapping("add")
//    public ResponseEntity<Map<String, Object>> add(@RequestBody UserCreateDto userCreateDto){
//        Map<String, Object> response = new HashMap<>();
//        response.put("status", "success");
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("users", userService.create(userCreateDto));
//        response.put("data", data);
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//    }

    @PutMapping("{id}")
    public Users add(@PathVariable("id") UUID idUsers, @RequestBody Users users){
        return userService.update(idUsers, users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
