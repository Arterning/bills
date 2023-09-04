package cn.ning.money.book.third.api;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    String appId;

    String grantType = "refreshToken";

}
