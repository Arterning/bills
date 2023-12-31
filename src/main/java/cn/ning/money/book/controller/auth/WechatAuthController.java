package cn.ning.money.book.controller.auth;

import cn.ning.money.book.common.anotations.CommonLog;
import cn.ning.money.book.common.enums.BusinessType;
import cn.ning.money.book.constant.UrlConstant;
import cn.ning.money.book.service.WechatAuthService;
import cn.ning.money.book.utils.HttpUtil;
import cn.ning.money.book.vo.LoginSuccessVO;
import cn.ning.money.book.vo.Result;
import cn.ning.money.book.vo.WechatUserVO;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Api(value = "WechatAuthController", tags = { "微信授权接口" })
@RestController
@RequestMapping("/wx")
public class WechatAuthController {
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    private WechatAuthService wechatAuthService;

    @ApiOperation(value = "获取微信openId")
    @GetMapping("/openId/{code}")
    public Result<?> getOpenId(@Validated @NotNull(message = "code不能为空") @PathVariable("code") String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String result = HttpUtil.doGet(UrlConstant.wechatOpenIdUrl, map);
        return Result.success(JSON.parse(result));
    }

    @CommonLog(title = "微信登录授权", businessType = BusinessType.GRANT)
    @ApiOperation(value = "微信登录授权")
    @PostMapping("/login")
    public Result<?> wechatLogin(@RequestBody @Validated  WechatUserVO vo) {
        LoginSuccessVO successVO = wechatAuthService.wechatLogin(vo);
        return Result.success(successVO);
    }
}
