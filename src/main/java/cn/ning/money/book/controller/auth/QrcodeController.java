package cn.ning.money.book.controller.auth;

import cn.ning.money.book.bo.QrCodeInfoBO;
import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.service.WechatAuthService;
import cn.ning.money.book.utils.*;
import cn.ning.money.book.vo.QrcodeAuthorizationVO;
import cn.ning.money.book.vo.Result;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "QrcodeController", tags = { "扫码授权接口" })
@RestController
@RequestMapping("/qrcode")
public class QrcodeController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private WechatAuthService wechatAuthService;
    @Autowired
    private SpringContextUtil springContextUtil;

    @ApiOperation(value = "二维码生成")
    @GetMapping
    public void getQrcode(String uuid) {
        HttpServletResponse response = ServletUtil.getResponse();
        // 设置浏览器不要缓存页面
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            // 如果uuid为空或缓存中没有uuid
            if (StringUtils.isBlank(uuid) || !redisUtil.hasKey(uuid)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().write(JSON.toJSONString(Result.error(CodeMsg.PARAMETER_ILLEGAL)));
                // 关闭writer
                response.getWriter().close();
            } else {
                response.setContentType("image/jpeg");
                // 正式环境才启用二维码信息
                if (springContextUtil.isProEnv()) {
                    String accessToken = wechatAuthService.getAccessToken();
                    byte[] bytes = wechatAuthService.getMiniAppQrcode(accessToken, uuid, "pages/detail/index");
                    response.getOutputStream().write(bytes);
                }
                // 关闭输出流
                response.getOutputStream().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "生成UUID")
    @GetMapping("/uuid")
    public Result<?> generateUUID() {
        // 生成32位的UUID（只含字母与数字）
        String uuid = IDUtil.randomUUID();
        // 将UUID放入Redis中，并设置60s过期时间
        redisUtil.set(uuid, new QrCodeInfoBO(), 60);
        return Result.success(uuid);
    }

    @ApiOperation(value = "扫描二维码")
    @PutMapping("/scan")
    public Result<?> scanQrcode(String uuid) {
        if (StringUtils.isBlank(uuid) || !redisUtil.hasKey(uuid))
            return Result.error(CodeMsg.PARAMETER_ILLEGAL);
        // 如果redis中存在，则更新状态
        QrCodeInfoBO info = (QrCodeInfoBO) redisUtil.get(uuid);
        // 判断是否扫描过
        if (info.getIsScanned())
            return Result.error(CodeMsg.OPERATE_FAILED);
        info.setIsScanned(true);
        info.setScannedTime(DateUtil.getCurrentTime());
        // 扫描后会有30s过期时间
        redisUtil.set(uuid, info, 30);
        return Result.success();
    }

    @ApiOperation(value = "授权登录")
    @PutMapping("/authorize")
    public Result<?> confirm(@RequestBody @Validated QrcodeAuthorizationVO vo) {
        if (!redisUtil.hasKey(vo.getUuid()))
            return Result.error(CodeMsg.PARAMETER_ILLEGAL);
        // 二维码需要已扫描过
        QrCodeInfoBO info = (QrCodeInfoBO) redisUtil.get(vo.getUuid());
        if (!info.getIsScanned())
            return Result.error(CodeMsg.OPERATE_FAILED);
        // 更新token
        info.setToken(vo.getToken());
        // 授权后会有30s过期时间
        redisUtil.set(vo.getUuid(), info, 30);
        return Result.success();
    }

    @ApiOperation(value = "获取二维码信息")
    @GetMapping("/info")
    public Result<?> getInfo(String uuid) {
        if (StringUtils.isBlank(uuid))
            return Result.error(CodeMsg.PARAMETER_ILLEGAL);
        QrCodeInfoBO info = (QrCodeInfoBO) redisUtil.get(uuid);
        // 如不存在则为过期
        if (info == null) {
            info = new QrCodeInfoBO();
            info.setIsExpired(true);
        }
        return Result.success(info);
    }

}
