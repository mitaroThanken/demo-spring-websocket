package demo.websocket.client.example1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class ClientWebSocketConfig {

    @Bean
    WebSocketConnectionManager webSocketConnectionManager(
            final WebSocketClient webSocketClient,
            final WebSocketHandler webSocketHandler) {
        final var manager = new WebSocketConnectionManager(
                webSocketClient,
                webSocketHandler,
                "ws://127.0.0.1:8080/websocket");
        return manager;
    }

    @Bean
    WebSocketClient webSocketClient() {
        return new StandardWebSocketClient();
    }

    @Bean
    WebSocketHandler webSocketHandler() {
        return new ClientWebSocketHandler();
    }
}
