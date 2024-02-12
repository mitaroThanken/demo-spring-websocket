package demo.websocket.server.example3.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public class FileStreamingResponseBody implements StreamingResponseBody {
    private static final Logger log = LoggerFactory.getLogger(FileStreamingResponseBody.class);

    private final Path path;

    public FileStreamingResponseBody(Path path) {
        this.path = path;
        if (!Files.exists(path)) {
            throw new StorageFileNotFoundException("Could not read file: " + path);
        }
    }

    public String getFileName() {
        return path.getFileName().toString();
    }

    public long size() throws IOException {
        return Files.size(path);
    }

    @Override
    public void writeTo(@NonNull OutputStream outputStream) throws IOException {
        log.info("Response: " + path);
        final StopWatch stopWatch = new StopWatch("transfer");
        stopWatch.start();
        try (InputStream inputStream = Files.newInputStream(path)) {
            inputStream.transferTo(outputStream);
        }
        stopWatch.stop();
        log.info("Response: Finish: " + stopWatch.shortSummary());
    }

}
