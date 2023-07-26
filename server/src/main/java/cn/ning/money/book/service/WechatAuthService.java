package cn.ning.money.book.service;

import cn.ning.money.book.entity.WechatUserEntity;
import cn.ning.money.book.vo.LoginSuccessVO;
import cn.ning.money.book.vo.WechatUserVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;


public interface WechatAuthService extends IService<WechatUserEntity> {

    WechatUserEntity getByOpenId(String openId);

    LoginSuccessVO wechatLogin(WechatUserVO vo);

    /**
     * 获取接口凭证
     */
    String getAccessToken();

    /**
     * 获取小程序码
     */
    byte[] getMiniAppQrcode(String accessToken, String scene, String pagePath) throws IOException;
}
