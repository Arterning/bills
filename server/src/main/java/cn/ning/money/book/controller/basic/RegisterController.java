package cn.ning.money.book.controller.basic;

import cn.ning.money.book.constant.CodeMsg;
import cn.ning.money.book.constant.SexConstant;
import cn.ning.money.book.entity.UserEntity;
import cn.ning.money.book.service.UserService;
import cn.ning.money.book.utils.PasswordUtil;
import cn.ning.money.book.vo.RegisterVO;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "LoginController", tags = { "用户注册接口" })
@RestController
@RequestMapping(value = "/register")
public class RegisterController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册用户")
    @PostMapping
    public Result<?> register(@RequestBody @Validated RegisterVO vo) {
        if (userService.getByName(vo.getUsername()) != null) {
            return Result.error(CodeMsg.USERNAME_EXIST);
        }
        if (!PasswordUtil.check(vo.getPassword())) {
            return Result.error(CodeMsg.PSW_FORMAT_ERROR);
        }
        if (SexConstant.MAN !=vo.getSex() && SexConstant.WOMAN != vo.getSex()) {
            return Result.error(CodeMsg.SEX_FORMAT_ERROR);
        }
        UserEntity userDO = new UserEntity();
        BeanUtils.copyProperties(vo, userDO);
        userDO.setCredential(PasswordUtil.encoder(vo.getPassword()));
        userService.saveWithDefaultRole(userDO);
        return Result.success();
    }
}
