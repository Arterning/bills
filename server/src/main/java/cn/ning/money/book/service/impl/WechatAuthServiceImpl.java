package cn.ning.money.book.service.impl;

import cn.ning.money.book.common.config.sercurity.JWTConfig;
import cn.ning.money.book.common.manager.AsyncManager;
import cn.ning.money.book.constant.RedisKey;
import cn.ning.money.book.constant.UrlConstant;
import cn.ning.money.book.entity.MenuEntity;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.entity.WechatUserEntity;
import cn.ning.money.book.mapper.WechatUserMapper;
import cn.ning.money.book.utils.HttpUtil;
import cn.ning.money.book.utils.PasswordUtil;
import cn.ning.money.book.utils.RedisUtil;
import cn.ning.money.book.utils.SpringContextUtil;
import cn.ning.money.book.vo.LoginSuccessVO;
import cn.ning.money.book.vo.WechatUserVO;
import cn.ning.money.book.service.MenuService;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.service.WechatAuthService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class WechatAuthServiceImpl extends ServiceImpl<WechatUserMapper, WechatUserEntity> implements WechatAuthService {
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.appSecret}")
    private String appSecret;

    @Autowired
    private WechatUserMapper wechatUserMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public WechatUserEntity getByOpenId(String openId) {
        QueryWrapper<WechatUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id",openId);
        return wechatUserMapper.selectOne(queryWrapper);
    }

    @Transactional
    @Override
    public LoginSuccessVO wechatLogin(WechatUserVO vo) {
        UserEntity userDO;
        WechatUserEntity wechatUserEntity = getByOpenId(vo.getOpenId());
        if (wechatUserEntity == null) {
            // 先注册
            UserEntity userTemp = new UserEntity();
            BeanUtils.copyProperties(vo, userTemp);
            userTemp.setCredential(PasswordUtil.encoder(PasswordUtil.randomPsw()));
            // 保存用户
            userService.saveWithDefaultRole(userTemp);
            userDO = userService.getByName(vo.getUsername());
            WechatUserEntity wechatUserTemp = new WechatUserEntity();
            wechatUserTemp.setUserId(userDO.getId());
            wechatUserTemp.setOpenId(vo.getOpenId());
            // 保存到微信用户表
            save(wechatUserTemp);
        }else {
            userDO = userService.getById(wechatUserEntity.getUserId());
        }
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        BeanUtils.copyProperties(userDO, loginSuccessVO);
        loginSuccessVO.setToken(generateToken(userDO.getId()));
        // 添加openId作为返回
        loginSuccessVO.setOpenId(vo.getOpenId());
        return loginSuccessVO;
    }

    @Override
    public String getAccessToken() {
        if (redisUtil.hasKey(RedisKey.WECHAT_ACCESS_TOKEN)) {
            return (String) redisUtil.get(RedisKey.WECHAT_ACCESS_TOKEN);
        }
        // 如果缓存中没有，则请求出来在放入redis
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credential");
        map.put("appid", appId);
        map.put("secret", appSecret);
        String result = HttpUtil.doGet(UrlConstant.wechatAccessTokenUrl, map);
        JSONObject obj = JSON.parseObject(result);
        // 解析到token和过期时间
        AsyncManager.me().execute(
                () -> redisUtil.set(RedisKey.WECHAT_ACCESS_TOKEN, obj.get("access_token"), Integer.parseInt(obj.get("expires_in").toString()) - 60)
        );
        return obj.get("access_token").toString();
    }

    @Override
    public byte[] getMiniAppQrcode(String accessToken, String scene, String pagePath) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("scene", scene);
        map.put("page", pagePath);
        return HttpUtil.post(UrlConstant.wechatMiniAppQrcodeUnlimitedUrl + accessToken, JSON.toJSONString(map));
    }

    private String generateToken(Long userId) {
        List<MenuEntity> menuDOList = menuService.getUserMenus(userId);
        List<String> permissionList = new ArrayList<>();
        // 跳过空权限字符（注：菜单和目录没有权限字符）
        menuDOList.stream().filter(n -> StringUtils.isNoneBlank(n.getPermissionSign())).forEach(n -> permissionList.add(n.getPermissionSign()));
        JWTConfig jwtConfig = SpringContextUtil.getBean(JWTConfig.class);
        return jwtConfig.createToken(userId.toString(),permissionList);
    }
}
