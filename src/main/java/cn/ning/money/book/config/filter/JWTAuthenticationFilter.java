package cn.ning.money.book.config.filter;

import cn.ning.money.book.config.sercurity.JWTConfig;
import cn.ning.money.book.config.sercurity.JWTUser;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.vo.LoginVO;
import cn.ning.money.book.vo.LoginSuccessVO;
import cn.ning.money.book.vo.Result;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.utils.SpringContextUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户认证过滤器
 * 只处理/user/login
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginVO loginVO = new ObjectMapper().readValue(request.getInputStream(), LoginVO.class);// 从输入流中获取到登录的信息
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginVO.getUsername(), loginVO.getPassword());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户认证成功调用的方法
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        JWTUser jwtUser = (JWTUser) authResult.getPrincipal();
        log.info("JWTUser:{}", jwtUser.toString());
        List<String> permissionList = new ArrayList<>();
        jwtUser.getAuthorities().forEach(n -> permissionList.add(n.getAuthority()));
        // 通过获取Spring上下文来获取JWTConfig对象
        JWTConfig jwtConfig = SpringContextUtil.getBean(JWTConfig.class);
        String token = jwtConfig.createToken(jwtUser.getId().toString(), permissionList);
        UserService userService = SpringContextUtil.getBean(UserService.class);
        UserEntity userDO = userService.getById(jwtUser.getId());
        LoginSuccessVO vo = new LoginSuccessVO();
        BeanUtils.copyProperties(userDO, vo);
        vo.setToken(token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.success(vo)));
    }

    /**
     * 用户认证失败回调
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JSON.toJSONString(Result.error(CodeMsg.LOGIN_ERROR), SerializerFeature.WriteMapNullValue));
    }
}
