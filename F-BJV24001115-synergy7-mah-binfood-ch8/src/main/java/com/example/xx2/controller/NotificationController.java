package com.example.xx2.controller;

import com.example.xx2.model.dto.NotificationRequestDto;
import com.example.xx2.model.dto.NotificationResponseDto;
import com.example.xx2.service.FCMService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {
    @Autowired
    private FCMService fcmService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody NotificationRequestDto request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageToToken(request);
        return new ResponseEntity<>(new NotificationResponseDto(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

}
