package cn.ning.money.book.controller.record;

import cn.ning.money.book.vo.Result;
import cn.ning.money.book.entity.RecordTypeEntity;
import cn.ning.money.book.service.RecordTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "RecordTypeController", tags = { "记账类别访问接口" })
@RestController
@RequestMapping("/recordType")
public class RecordTypeController {
    @Autowired
    private RecordTypeService recordTypeService;

    @ApiOperation(value = "获取所有记账类别")
    @GetMapping
    public Result<?> getRecordTypes() {
        List<RecordTypeEntity> recordTypes = recordTypeService.findAll();
        return Result.success(recordTypes);
    }
}
