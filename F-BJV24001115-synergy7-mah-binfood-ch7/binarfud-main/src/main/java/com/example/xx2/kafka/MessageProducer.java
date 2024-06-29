package com.example.xx2.kafka;

import com.example.xx2.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String theMessage) {
        kafkaTemplate.send(topic, theMessage);
    }

//    public void sendMessage(String topic, Product product){
//        kafkaTemplate.send(topic, product);
//    }
}
