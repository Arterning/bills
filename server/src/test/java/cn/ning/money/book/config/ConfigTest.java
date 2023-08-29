package cn.ning.money.book.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfigTest {

    @Value("${test.name}")
    private String test_name;

    @Value("${test.value}")
    private String test_value;

    @Test
    public void test(){
        System.out.println(test_name);
        System.out.println(test_value);
    }


}
