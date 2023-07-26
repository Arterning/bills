package cn.ning.money.book.common.anotations;

import java.lang.annotation.*;

/**
 *  
 *  
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
}
