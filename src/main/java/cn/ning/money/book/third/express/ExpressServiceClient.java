package cn.ning.money.book.third.express;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "expressService", url = "http://localhost:3000")
public interface ExpressServiceClient {

    @GetMapping(value = "/express", consumes = MediaType.APPLICATION_JSON_VALUE)
    String test();

}

