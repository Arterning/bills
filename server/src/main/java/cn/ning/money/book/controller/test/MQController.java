package cn.ning.money.book.controller.test;

import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "HelloController", tags = { "测试接口"})
@RequestMapping("/test/mq")
@AllArgsConstructor
public class MQController {

    @ApiOperation(value = "index")
    @GetMapping()
    public Result<?> index() {
        return Result.success("index");
    }


}
