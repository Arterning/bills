package cn.ning.money.book.controller.basic;

import cn.ning.money.book.common.LocalUser;
import cn.ning.money.book.common.anotations.LoginRequired;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.vo.AccountBaseSettingVO;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "AccountController", tags = { "账户设置接口" })
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "账户基本设置")
    @LoginRequired
    @PutMapping(value = "/baseSetting")
    public Result<?> saveInfo(@RequestBody @Validated AccountBaseSettingVO vo) {
        UserEntity user = LocalUser.get();
        user.setNickname(vo.getNickname());
        user.setSex(vo.getSex());
        userService.updateById(user);
        return Result.success();
    }

}
