package cn.ning.money.book.modules.rabbitmq;

import cn.ning.money.book.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "HelloController", tags = { "测试接口"})
@RequestMapping("/test/mq")
@AllArgsConstructor
public class MQController {

    private final SendService sendService;

    @GetMapping("/send")
    public Result<?> sendMsgToMq(@RequestParam String msg){
        sendService.sendMsg(msg);
        return Result.success("sendMsgToMq");
    }

    @GetMapping("/sendTopic")
    public Result<?> sendTopicMsgToMq(@RequestParam String msg, @RequestParam String route){
        sendService.sendTopicMsg(msg, route);
        return Result.success("sendMsgToMq");
    }

    @ApiOperation(value = "index")
    @GetMapping()
    public Result<?> index() {
        return Result.success("index");
    }


}
