package cn.ning.money.book.third.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "thirdPartyClient", url = "http://aigc.softsugar.com/api")
public interface AigcClient {

    @PostMapping(value = "/uc/v1/access/api/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    LoginResponse sendApiRequest(@RequestBody LoginRequest aiRequest);

    @PostMapping(value = "/uc/v1/access/api/token/refresh", consumes = MediaType.APPLICATION_JSON_VALUE)
    RefreshTokenResponse refreshToken(@RequestHeader("Authorization") String refreshToken,  @RequestBody RefreshTokenRequest request);

    @PostMapping(value = "/2dvh/v1/material/live/channel/close", consumes = MediaType.APPLICATION_JSON_VALUE)
    CloseSessionResponse closeSession(@RequestHeader("Authorization") String token, @RequestBody CloseSessionRequest aiRequest);

    @PostMapping(value = "/2dvh/v2/material/live/channel/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    OpenSessionResponse openSession(@RequestHeader("Authorization") String token, @RequestBody OpenSessionRequest aiRequest);

    @GetMapping(value = "/2dvh/v1/material/live/channel/running/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    ListSessionResponse listOpenSession(@RequestHeader("Authorization") String token);


}
