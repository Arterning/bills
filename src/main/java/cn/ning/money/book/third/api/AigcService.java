package cn.ning.money.book.third.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AigcService {

    private final AigcClient thirdPartyClient;

    @Value("${third.appId}")
    private String appId;

    @Value("${third.appKey}")
    private String appKey;

    String token = "Bearer ZjkxZjU4NzAxOGJhNDJhMmEwZmRiOTU0YmQ0ZGZkYzAwZjY0N2I4OS03MzJhLTQzYWItYmU3NC04ZTc2Y2JlZTcwOGQ";


    public AigcService(AigcClient thirdPartyClient) {
        this.thirdPartyClient = thirdPartyClient;
    }

    public LoginResponse login() {
        LoginRequest requestObject = new LoginRequest(appId, appKey);
        LoginResponse tokenResponse = thirdPartyClient.sendApiRequest(requestObject);
        return tokenResponse;
    }

    public RefreshTokenResponse refreshToken(String refreshToken) {
        RefreshTokenRequest requestObject = new RefreshTokenRequest();
        requestObject.setAppId(appId);
        String refresh = "Bearer " + refreshToken;
        RefreshTokenResponse result = thirdPartyClient.refreshToken(refresh, requestObject);
        return result;
    }

    public OpenSessionResponse openSession() {
        OpenSessionRequest openSessionRequest = new OpenSessionRequest();
        openSessionRequest.setParam("{\"version\":\"0.0.1\",\"recycle\":0,\"stream_mode\":true,\"sceneList\":[{\"digital_role\":{\"id\":1,\"face_feature_id\":\"0325_nina_s3_beauty\",\"name\":\"小李\",\"url\":\"https://dwg-aigc-paas-test.oss-cn-hangzhou.aliyuncs.com/materials/77/0325_nina_s3_beauty.zip\",\"position\":{\"x\":10,\"y\":60},\"scale\":1},\"tts_config\":{\"id\":\"nina\",\"name\":\"nina\",\"vendor_id\":3,\"language\":\"zh-CN\",\"pitch_offset\":0.0,\"speed_ratio\":1,\"volume\":400},\"tts_query\":{\"content\":\"商汤科技是一家行业领先的人工智能软件公司，以坚持原创，让AI引领人类进步为使命。\",\"ssml\":false},\"audios\":[{\"url\":\"http://dwg-aigc-paas.oss-cn-hangzhou.aliyuncs.com/test/softsugar.mp3\"}],\"backgrounds\":[{\"type\":0,\"name\":\"背景\",\"url\":\"https://dwg-aigc-paas.oss-cn-hangzhou.aliyuncs.com/test/background.png\",\"rect\":[0,0,1080,1920],\"cycle\":true,\"start\":0,\"duration\":-1}],\"foregrounds\":[{\"type\":0,\"name\":\"前景1\",\"url\":\"https://dwg-aigc-paas.oss-cn-hangzhou.aliyuncs.com/test/frontgroud.png\",\"rect\":[0,0,310,88],\"start\":0,\"duration\":100}],\"effects\":{\"version\":\"1.0\",\"beautify\":{}}}]}");
        OpenSessionResponse openSessionResponse = thirdPartyClient.openSession(token, openSessionRequest);
        return openSessionResponse;
    }

    public CloseSessionResponse closeSession(String sessionId) {
        CloseSessionRequest closeSessionRequest = new CloseSessionRequest();
        closeSessionRequest.setSessionId(sessionId);
        CloseSessionResponse closeSessionResponse = thirdPartyClient.closeSession(token, closeSessionRequest);
        return closeSessionResponse;
    }

    public ListSessionResponse listOpenSession() {
        ListSessionResponse listSessionResponse = thirdPartyClient.listOpenSession(token);
        return listSessionResponse;
    }



}
