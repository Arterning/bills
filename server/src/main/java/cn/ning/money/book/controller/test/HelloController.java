package cn.ning.money.book.controller.test;

import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "HelloController", tags = { "测试接口"})
@RequestMapping("/hello")
public class HelloController {

    @ApiOperation(value = "你爱我 我爱你")
    @GetMapping()
    public Result<?> hello() {
        return Result.success("你爱我，我爱你");
    }
}
