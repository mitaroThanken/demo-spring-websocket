package demo.websocket.server.example3.storage;

import java.io.IOException;
import java.time.Instant;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/files")
public class FileUploadController {
    private final StorageService storageService;

    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public Stream<String> listUploadFiles() throws IOException {
        return storageService.loadAll().map((path) -> path.getFileName().toString());
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<StreamingResponseBody> serveFile(@PathVariable("filename") String filename,
            @RequestHeader(name = HttpHeaders.IF_MODIFIED_SINCE, required = false) Instant ifModifiedSince)
            throws IOException {
        FileStreamingResponseBody response = storageService.loadAsStreamingResponseBody(filename);

        if ((ifModifiedSince != null) && ifModifiedSince.equals(response.lastModified())) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getFileName() + "\"")
                .lastModified(response.lastModified())
                .contentLength(response.size())
                .body(response);
    }

    @PostMapping("/")
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
