package cn.ning.money.book.controller.basic;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@Api(value = "CommonLogController", tags = { "日志相关接口" })
@RestController
@RequestMapping("/log")
public class CommonLogController {

}
