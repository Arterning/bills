package cn.ning.money.book.third.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String appId;
    private String sign;

    private long timestamp;

    private String grantType;

    // Constructors, getters and setters
    public LoginRequest(String appId, String appKey) {
        this.appId = appId;
        this.timestamp = System.currentTimeMillis();

        String signContent = appId + timestamp + appKey;
        this.sign = Utils.generateMD5(signContent);

        this.grantType = "sign";
    }

}
