package cn.ning.money.book.third.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {

    private final ThirdPartyClient thirdPartyClient;

    @Value("${third.appId}")
    private String appId;

    @Value("${third.appKey}")
    private String appKey;


    public ThirdPartyService(ThirdPartyClient thirdPartyClient) {
        this.thirdPartyClient = thirdPartyClient;
    }

    public TokenResponse login() {
        LoginRequest requestObject = new LoginRequest(appId, appKey);
        TokenResponse tokenResponse = thirdPartyClient.sendApiRequest(requestObject);
        return tokenResponse;
    }

    public RefreshTokenResponse refreshToken(String refreshToken) {
        RefreshTokenRequest requestObject = new RefreshTokenRequest();
        requestObject.setAppId(appId);
        String refresh = "Bearer " + refreshToken;
        RefreshTokenResponse result = thirdPartyClient.refreshToken(refresh, requestObject);
        return result;
    }

}
