package cn.ning.money.book.common.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<HttpServletRequestReplacedFilter> httpServletRequestReplacedRegistration() {
        FilterRegistrationBean<HttpServletRequestReplacedFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/*");
        registration.setName("httpServletRequestReplacedFilter");
        registration.setOrder(1);
        return registration;
    }
}