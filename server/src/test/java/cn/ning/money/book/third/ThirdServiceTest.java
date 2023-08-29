package cn.ning.money.book.third;


import cn.ning.money.book.third.api.ThirdPartyService;
import cn.ning.money.book.third.express.ExpressServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ThirdServiceTest {

    @Resource
    ThirdPartyService thirdService;

    @Resource
    ExpressServiceClient expressServiceClient;

    @Test
    void test() {
        String result = thirdService.sendApiRequest();
        System.out.println(result);
    }

    @Test
    void test_express() {
        String test = expressServiceClient.test();
        System.out.println(test);
    }


}
