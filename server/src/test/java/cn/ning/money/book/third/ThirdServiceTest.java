package cn.ning.money.book.third;


import cn.ning.money.book.third.api.RefreshTokenResponse;
import cn.ning.money.book.third.api.ThirdPartyService;
import cn.ning.money.book.third.api.TokenResponse;
import cn.ning.money.book.third.express.ExpressServiceClient;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ThirdServiceTest {

    @Resource
    ThirdPartyService thirdService;

    @Resource
    ExpressServiceClient expressServiceClient;

    //登录。心跳检测。内部使用的关闭接口，10秒钟期限
    @Test
    void test_login() {
        TokenResponse result = thirdService.login();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_refresh_token() {
        RefreshTokenResponse result = thirdService.refreshToken("MWVlNDUyNTUxM2FjM2RhNmFjZjI0MjUzYTVmYzNiYjUwN2Y4MWZmMC0yNzJiLTQ1ZDAtOGExMi02NDBhOTU4N2ZkNzU");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_express() {
        String test = expressServiceClient.test();
        System.out.println(test);
    }


}
