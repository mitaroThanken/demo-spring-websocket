package demo.websocket.client.example2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.socket.client.WebSocketConnectionManager;

@Configuration
public class ClientWebSocketJobConfiguration {
    @Bean
    Step step(
            final JobRepository jobRepository,
            final PlatformTransactionManager transactionManager,
            final WebSocketConnectionManager manager) {
        return new StepBuilder("clientWebSocketStep", jobRepository)
                .tasklet(
                        (contribution, chunkContext) -> {
                            manager.start();
                            Thread.sleep(10000);
                            manager.stop();
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
