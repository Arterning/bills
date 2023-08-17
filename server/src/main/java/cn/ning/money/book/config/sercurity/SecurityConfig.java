package cn.ning.money.book.config.sercurity;

import cn.ning.money.book.config.filter.JWTAuthenticationFilter;
import cn.ning.money.book.config.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * permitAll没有绕过spring security，其中包含了登录的以及匿名的。
     * 这里的代码表示register没有任何权限也可以访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/register").permitAll() // 不拦截 /api/register 路径
                .anyRequest().authenticated();;
    }

    /**
     * 配置哪些请求可以被过滤
     * ingore是完全绕过了spring security的所有filter，相当于不走spring security
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/user/register")
                .antMatchers("/doc.html")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/**")
                .antMatchers("/favicon.ico")
                // 微信获取openId的Url不拦截
                .antMatchers("/wx/openId/*")
                .antMatchers("/wx/login")
                // 登录二维码不拦截
                .antMatchers("/qrcode/**")
                .antMatchers("/register")
                .antMatchers("/**/*.html")
                .antMatchers("/test/**");
        web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
            @Override
            protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
                WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
                root.setDefaultRolePrefix(""); // remove the prefix ROLE_
                return root;
            }
        });
    }

    /**
     * 返回跨域配置
     * 所谓的Cors配置，就是用来限制请求只能从哪些源过来
     * cfg.addAllowedHeader("*")：允许所有的请求头。
     * cfg.addAllowedMethod("*")：允许所有的 HTTP 方法。
     * cfg.addAllowedOriginPattern("*")：允许来自任何源的请求。
     * cfg.setAllowCredentials(true)：允许发送身份验证凭证（如 cookies、HTTP 认证等）。
     * cfg.checkOrigin("*")：对来源进行检查，允许所有来源。
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        return req -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.addAllowedHeader("*");
            cfg.addAllowedMethod("*");
            cfg.addAllowedOriginPattern("*");
            cfg.setAllowCredentials(true);
            cfg.checkOrigin("*");
            return cfg;
        };
    }

}
