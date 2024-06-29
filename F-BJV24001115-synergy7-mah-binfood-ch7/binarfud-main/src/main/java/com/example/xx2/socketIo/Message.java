package com.example.xx2.socketIo;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String to;
    private String content;
}
