package cn.ning.money.book.controller.basic;

import cn.ning.money.book.vo.LoginVO;
import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "LoginController", tags = { "用户登录接口" })
@RestController
@RequestMapping(value = "/")
public class LoginController {

    @ApiOperation(value = "用户登录")
    @PostMapping("user/login")
    public Result<?> userLogin(@RequestBody LoginVO dto) {
        return Result.success();
    }
}
