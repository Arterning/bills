package cn.ning.money.book.minio;

import cn.ning.money.book.controller.minio.MinioService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MinioTest {

    @Resource
    MinioService minioService;

    @Test
    public void testUpload() {
        System.out.println(minioService);
    }

}
