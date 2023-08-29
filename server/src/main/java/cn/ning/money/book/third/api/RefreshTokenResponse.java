package cn.ning.money.book.third.api;

import lombok.Data;


@Data
public class RefreshTokenResponse {

//    code	Integer	True	0 - 成功, 其他 - 异常
//    message	String	True	异常详细信息
//    data	Object	False	数据对象 ，错误返回null
//  - accessToken	String	True	访问凭证，默认有效期8小时
//  - expiresIn	Integer	True	访问凭证剩余生命周期，单位：秒
//  - refreshToken	String	True	Refresh Token
//  - refreshTokenExpiresIn	Integer	True	refreshToken剩余生命周期，单位：秒
    private int code;
    private String message;
    private DataBean data;

    @Data
    public static class DataBean {
        String accessToken;
        Integer expiresIn;
        String refreshToken;

        String refreshTokenExpiresIn;
        Integer refreshTokenExpiresInInt;

    }


}
