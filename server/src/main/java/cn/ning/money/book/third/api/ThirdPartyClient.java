package cn.ning.money.book.third.api;

import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "thirdPartyClient", url = "http://aigc.softsugar.com/api")
public interface ThirdPartyClient {

    @PostMapping(value = "/uc/v1/access/api/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    TokenResponse sendApiRequest(@RequestBody LoginRequest aiRequest);


    @PostMapping(value = "/uc/v1/access/api/token/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    String refreshToken(@RequestHeader("Authorization") String refreshToken,  @RequestBody RefreshTokenRequest request);


}
