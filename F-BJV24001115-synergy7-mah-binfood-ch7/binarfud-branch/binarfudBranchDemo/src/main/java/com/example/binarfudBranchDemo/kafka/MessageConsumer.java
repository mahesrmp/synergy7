package com.example.binarfudBranchDemo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    @KafkaListener(topics = "coming-soon-product", groupId = "my-group-id")
    public void listen(String theMessage){
        System.out.println("Message received: "+ theMessage);
    }
}
