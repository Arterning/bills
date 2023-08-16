package cn.ning.money.book.controller.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/test/upload")
public class UploadController {

    private final MinioService minioService;

    @Autowired
    public UploadController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String response = minioService.uploadFile(file);
        return ResponseEntity.ok(response);
    }
}
