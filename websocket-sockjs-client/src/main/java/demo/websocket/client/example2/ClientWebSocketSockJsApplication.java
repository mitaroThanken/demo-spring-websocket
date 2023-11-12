package demo.websocket.client.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClientWebSocketSockJsApplication {

    public static void main(final String[] args) {
        final var app = new SpringApplicationBuilder(ClientWebSocketSockJsApplication.class)
                .web(WebApplicationType.NONE)
                .build();

        System.exit(SpringApplication.exit(app.run(args)));
    }
}
