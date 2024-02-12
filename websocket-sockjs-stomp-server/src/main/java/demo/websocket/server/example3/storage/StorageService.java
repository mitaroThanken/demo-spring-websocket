package demo.websocket.server.example3.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    FileStreamingResponseBody loadAsStreamingResponseBody(String filename);

    void deleteAll();
}
