package cn.ning.money.book.third;


import cn.ning.money.book.third.api.*;
import cn.ning.money.book.third.express.ExpressServiceClient;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ThirdServiceTest {

    @Resource
    AigcService thirdService;

    @Resource
    ExpressServiceClient expressServiceClient;

    //登录。心跳检测。内部使用的关闭接口，10秒钟期限
    @Test
    void test_login() {
        LoginResponse result = thirdService.login();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_openSession() {
        OpenSessionResponse result = thirdService.openSession();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_closeSession() {
        CloseSessionResponse result = thirdService.closeSession("3f6ee291eec74fe0b11c45ba53e259c2");
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_listOpenSession() {
        ListSessionResponse result = thirdService.listOpenSession();
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    void test_express() {
        String test = expressServiceClient.test();
        System.out.println(test);
    }


}
