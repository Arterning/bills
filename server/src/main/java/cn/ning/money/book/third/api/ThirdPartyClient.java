package cn.ning.money.book.third.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "thirdPartyClient", url = "http://aigc.softsugar.com/api")
public interface ThirdPartyClient {

    @PostMapping(value = "/uc/v1/access/api/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    String sendApiRequest(@RequestBody LoginRequest aiRequest);

}
