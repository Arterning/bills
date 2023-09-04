package cn.ning.money.book.common.anotations;

import cn.ning.money.book.common.enums.BusinessType;

import java.lang.annotation.*;


@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonLog {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 业务
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
