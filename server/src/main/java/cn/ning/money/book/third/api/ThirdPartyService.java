package cn.ning.money.book.third.api;

import org.springframework.stereotype.Service;

@Service
public class ThirdPartyService {

    private final ThirdPartyClient thirdPartyClient;

    public ThirdPartyService(ThirdPartyClient thirdPartyClient) {
        this.thirdPartyClient = thirdPartyClient;
    }

    public String sendApiRequest() {
        LoginRequest requestObject = new LoginRequest("", "");
        return thirdPartyClient.sendApiRequest(requestObject);
    }

}
