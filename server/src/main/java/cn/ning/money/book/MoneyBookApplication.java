package cn.ning.money.book;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("cn.ning.money.book.mapper")
public class MoneyBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyBookApplication.class, args);
    }

}
