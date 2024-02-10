package demo.websocket.server.example3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import demo.websocket.server.example3.storage.StorageProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
public class ServerWebSocketSockJsStompApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerWebSocketSockJsStompApplication.class, args);
    }
}
