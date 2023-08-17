package cn.ning.money.book.modules.minio;

import cn.ning.money.book.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/test/upload")
public class UploadController {

    private final MinioService minioService;

    @Autowired
    public UploadController(MinioService minioService) {
        this.minioService = minioService;
    }


    /**
     * 提交文件
     * @param file
     * @param extra
     * @return
     */
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam(value = "extra", required = false) String extra) {
        String response = minioService.uploadFile(file);
        return ResponseEntity.ok(response);
    }

    /**
     * 提交简单表单
     * @param name
     * @param extra
     * @return
     */
    @PostMapping("/form")
    public Result<?> postForm(@RequestParam String name, @RequestParam String extra) {
        return Result.success(name + extra);
    }

}
