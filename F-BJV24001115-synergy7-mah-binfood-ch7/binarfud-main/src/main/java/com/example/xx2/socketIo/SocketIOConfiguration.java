package com.example.xx2.socketIo;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SocketIOConfiguration {
    private SocketIOServer socketIOServer;

    @Value("${socket-io.hostname}")
    private String hostname;

    @Value("${socket-io.port}")
    private int port;

    @Bean
    SocketIOServer socketIOServer(){
        Configuration configuration = new Configuration();
        configuration.setHostname(hostname);
        configuration.setPort(port);
        socketIOServer = new SocketIOServer(configuration);
        socketIOServer.start();

        socketIOServer.addConnectListener(socketIOClient -> {
//            System.out.println(socketIOClient.getHandshakeData().getHttpHeaders().get("Authorization"));
            System.out.println("A new client is connected to " + socketIOClient.getSessionId());
        });

        socketIOServer.addDisconnectListener(socketIOClient ->
                System.out.println("A new client is disconnected from " + socketIOClient.getSessionId()));

        return socketIOServer;
    }

    @PreDestroy
    public void stopSocketIOServer(){
        socketIOServer.stop();
    }

}
