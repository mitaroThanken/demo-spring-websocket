package demo.websocket.client.example3;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class ClientWebSocketJobConfiguration {
    @Bean
    Step step(
            final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager,
            final WebSocketStompClient client,
            final StompSessionHandler stompSessionHandler) {
        return new StepBuilder("clientWebSocketStep", jobRepository)
                .tasklet(
                        (contribution, chunkContext) -> {
                            final var session = client.connectAsync("http://127.0.0.1:8080/websocket-sockjs-stomp", stompSessionHandler).get();
                            Thread.sleep(10000);
                            session.disconnect();
                            return RepeatStatus.FINISHED;
                        },
                        transactionManager)
                .build();
    }

    @Bean
    Job job(final JobRepository jobRepository, final Step step) {
        return new JobBuilder("clientWebSocketJob", jobRepository)
                .start(step)
                .build();
    }
}
