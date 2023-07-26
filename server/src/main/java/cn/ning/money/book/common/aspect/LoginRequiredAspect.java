package cn.ning.money.book.common.aspect;

import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.common.config.sercurity.JWTConfig;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.exception.NotFoundException;
import cn.ning.money.book.exception.ParameterException;
import cn.ning.money.book.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Slf4j
public class LoginRequiredAspect implements Ordered {
    // 执行顺序，越小越先执行（遵从同心圆的概念）
    private final int order = 10;

    @Autowired
    private JWTConfig jwtConfig;
    @Autowired
    private UserService userService;

    private String token;
    private Long userId;

    @Pointcut("@annotation(cn.ning.money.book.common.anotations.LoginRequired)")
    public void doHandler(){

    }

    // 开始环绕
    @Around("doHandler()")
    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
        // step 1
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 从 http 请求头中取出 token
        token = request.getHeader("token");

        // 切面往下执行
        Object result = joinPoint.proceed();

        // step 5 将结果返回
        return result;
    }

    @Before("doHandler()")
    public void before(){
        // step 2
        // 进行token校验
        verifyHeader(token);
        // step 3
        UserEntity userDO = userService.getById(userId);
        if (userDO == null) {
            throw new NotFoundException("未找到指定用户");
        }
        LocalUser.set(userDO);
    }

    @After("doHandler()")
    public void after(){
        // step 4
        log.info("userId:{} 通过token验证",userId);
    }

    private void verifyHeader(String token) {
        if (StringUtils.isBlank(token)) {
            throw new ParameterException(CodeMsg.PARAMETER_ILLEGAL,"token不能为空");
        }
        Claims claims = jwtConfig.getTokenClaim(token);
        if (claims == null) {
            throw new ParameterException(CodeMsg.PARAMETER_ILLEGAL,"token不合法");
        }
        // 校验token是否过期
        if (jwtConfig.isTokenExpired(claims)) {
            throw new ParameterException(CodeMsg.PARAMETER_ILLEGAL,"token已过期");
        }
        userId = Long.valueOf(jwtConfig.getUserIdFromToken(claims));
    }

    @Override
    public int getOrder() {
        return order;
    }
}
